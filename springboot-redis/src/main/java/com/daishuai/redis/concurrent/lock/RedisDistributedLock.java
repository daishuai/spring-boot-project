package com.daishuai.redis.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description: Redis分布式锁实现
 * @Author: daishuai
 * @CreateDate: 2019/2/18 19:48
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RedisDistributedLock extends AbstractDistributedLock {

    private static final Logger log = LoggerFactory.getLogger(RedisDistributedLock.class);

    private RedisTemplate<Object, Object> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    private static final String UNLOCK_LUA;

    static {
        //Lua 脚本
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append(" return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append(" return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    public RedisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        //如果获取锁失败，按照传入的重试次数进行重试
        while (!result && retryTimes-- > 0) {
            try {
                log.info("lock failed, retrying...{}", retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }


    private boolean setRedis(final String key, final long expire) {
        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    return commands.set(key, uuid, "NX", "PX", expire);
                }
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("set redis occurred an exception:{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean releaseLock(String key) {
        //释放锁的时候，有可能因为持有锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            final List<String> keys = new ArrayList<String>();
            keys.add(key);
            final List<String> args = new ArrayList<String>();
            args.add(lockFlag.get());
            //使用lua脚本删除redis中匹配的value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            //Spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    //集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能翻开执行
                    //集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    //单机模式
                    if (nativeConnection instanceof Jedis) {
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            });
            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occurred exception :{}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Redis分布式锁存在的问题
     * ----将锁资源放入Redis，setIfAbsent(key, value)
     * ----设置过期时间，expire(key, expire, timeUnit)
     * ----释放锁，delete(key)
     * 1、在调用selfIfAbsent()方法后线程挂掉了，即没有给锁定的资源设置过期时间，默认是永不过期，那么这个锁就会一直存在，所以需要保证设置锁及其过期时间两个操作的原子性
     *   解决方法：jedis当中有这种源自操作的方法，通过RedisTemplate的execute方法获取到jedis里操作命令的对象，JedisCommands.set(key,value,"NX","PX",expire)
     *   NX:表示只用当前锁定资源不存在的时候才能SET成功。
     *   PX:expire表示锁定的资源的自动过期时间，单位是毫秒。
     * 2、线程T1获取锁，线程T1执行业务操作，由于某些原因阻塞了较长时间，锁自动过期，即锁自动释放了，线程T2获取锁，线程T1业务操作完成，释放锁（其实是释放的线程T2的锁）
     */

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //获取锁
            return true;
        }
        String redisValue = (String) redisTemplate.opsForValue().get(key);

        if (!StringUtils.isEmpty(redisValue) && Long.valueOf(redisValue) < System.currentTimeMillis()) {
            //锁过期
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key, value);

            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(redisValue)) {
                //获取锁
                return true;
            }
        }
        //等待锁
        return false;
    }
}


















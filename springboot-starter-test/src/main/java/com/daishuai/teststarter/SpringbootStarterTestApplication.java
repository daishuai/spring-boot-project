package com.daishuai.teststarter;

import com.daishuai.starter.HelloService;
import com.daishuai.teststarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootStarterTestApplication {

    @Autowired
    private HelloService helloService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStarterTestApplication.class, args);
    }


    @RequestMapping("/hello")
    public String hello(){
        return helloService.getName();
    }

    @RequestMapping("/get")
    public String getMoney(Integer id) {
        double money = userService.drawMoney(id);
        return "从ATM机中取出" + money + "元！";
    }
}

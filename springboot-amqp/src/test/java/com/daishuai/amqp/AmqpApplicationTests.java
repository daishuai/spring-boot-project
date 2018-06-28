package com.daishuai.amqp;

import com.daishuai.amqp.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

	@Test
	public void contextLoads() {
        Department department = new Department();
        department.setDepartment_id(10);
        department.setDepartment_name("开发部");
        department.setManager_id(111);
        department.setLocation_id(123);
	    rabbitTemplate.convertAndSend("rabbit.exchange","hello",department);
	}

	@Test
	public void amqpAdmin(){
	    //创建路由器Exchange
        /*Exchange exchange = new DirectExchange("rabbit.exchange");
        amqpAdmin.declareExchange(exchange);*/
        //创建队列Queue
        //amqpAdmin.declareQueue(new Queue("rabbit.queue"));
        //Binding(destination,destinationType,exchange,routingKey,arguments)
        Binding binding = new Binding("rabbit.queue",Binding.DestinationType.QUEUE,"rabbit.exchange","hello",null);
        amqpAdmin.declareBinding(binding);
        //删除队列Queue
        //amqpAdmin.deleteQueue("rabbit.queue");
    }


}

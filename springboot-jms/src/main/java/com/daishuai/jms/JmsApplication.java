package com.daishuai.jms;

import com.daishuai.jms.activemq.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class JmsApplication {


	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

}

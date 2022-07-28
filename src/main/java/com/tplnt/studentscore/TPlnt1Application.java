package com.tplnt.studentscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RefreshScope
public class TPlnt1Application {

	public static void main(String[] args) {
		SpringApplication.run(TPlnt1Application.class, args);
	}

}

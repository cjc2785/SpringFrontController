package com.ss.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringLmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLmsApplication.class, args);
	}
}

package org.microus.sample.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TodoBackendApplication {
	public static void main(String[] args) {

		SpringApplication.run(TodoBackendApplication.class,args);
		
	}

   
}

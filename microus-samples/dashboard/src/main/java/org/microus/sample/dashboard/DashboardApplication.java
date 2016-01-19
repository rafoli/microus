package org.microus.sample.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class DashboardApplication {
	public static void main(String[] args) {

		SpringApplication.run(DashboardApplication.class,args);
		
	}
}

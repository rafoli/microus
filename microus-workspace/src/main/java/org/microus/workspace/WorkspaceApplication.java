package org.microus.workspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class WorkspaceApplication {
	public static void main(String[] args) {

		SpringApplication.run(WorkspaceApplication.class,args);
		
	}
	
}

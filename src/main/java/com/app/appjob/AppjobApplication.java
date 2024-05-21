package com.app.appjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppjobApplication.class, args);
		System.out.println("Application started...");
	}

}

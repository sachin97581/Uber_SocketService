package com.example.UberSocketService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.uberProject_EntityService.model")
public class UberSocketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberSocketServiceApplication.class, args);
	}

}

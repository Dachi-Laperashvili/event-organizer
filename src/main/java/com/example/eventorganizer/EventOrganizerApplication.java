package com.example.eventorganizer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class EventOrganizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventOrganizerApplication.class, args);
	}

}
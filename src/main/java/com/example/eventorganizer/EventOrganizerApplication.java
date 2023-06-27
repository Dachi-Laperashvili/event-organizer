package com.example.eventorganizer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Event Organizer Application",
				version = "1.0.0",
				description = "application which allows group of people to organize an event together"
		)
)
public class EventOrganizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventOrganizerApplication.class, args);
	}

}
package com.tidz.relationships;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RelationshipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLinerunner(String args[]) {
		return runner -> {
			System.out.println("Hello World!");
		};
	}

}

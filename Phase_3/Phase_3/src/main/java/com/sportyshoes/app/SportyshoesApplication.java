package com.sportyshoes.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.sportyshoes.model")
@EnableJpaRepositories("com.sportyshoes.repo")
public class SportyshoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportyshoesApplication.class, args);
	}

}

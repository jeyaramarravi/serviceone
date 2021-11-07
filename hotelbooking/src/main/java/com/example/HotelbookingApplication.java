package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing
@EnableJpaRepositories
public class HotelbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelbookingApplication.class, args);
	}

}

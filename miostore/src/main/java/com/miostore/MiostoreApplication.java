package com.miostore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiostoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiostoreApplication.class, args);
	}

}

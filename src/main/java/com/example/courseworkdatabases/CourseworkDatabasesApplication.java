package com.example.courseworkdatabases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.courseworkdatabases.entity"})
@EnableJpaRepositories(basePackages = {"com.example.courseworkdatabases.repository"})
public class CourseworkDatabasesApplication {
	public static void main(String[] args) {
		SpringApplication.run(CourseworkDatabasesApplication.class, args);
	}

}

package com.example.jpa_02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleMyRepository.class)
public class Jpa02Application {

    public static void main(String[] args) {
        SpringApplication.run(Jpa02Application.class, args);
    }

}

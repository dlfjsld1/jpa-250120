package com.example.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// Post의 @EntityListeners로 감시로 어노테이션과 코드 추가
@EnableJpaAuditing
public class JpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
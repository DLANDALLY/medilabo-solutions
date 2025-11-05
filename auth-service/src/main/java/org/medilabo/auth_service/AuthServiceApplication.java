package org.medilabo.auth_service;

import org.medilabo.auth_service.entity.UserEntity;
import org.medilabo.auth_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(UserEntity.builder().username("admin").password(encoder.encode("admin123")).role("ADMIN").build());
				repo.save(UserEntity.builder().username("user").password(encoder.encode("user123")).role("USER").build());
			}
		};
	}
}

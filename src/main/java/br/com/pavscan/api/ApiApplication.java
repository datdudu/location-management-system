package br.com.pavscan.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.com.pavscan.api")
@EnableJpaRepositories( basePackages = "br.com.pavscan.api")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		System.out.println("API UP - SYSTEM");
    }
}

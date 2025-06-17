package com.example.olimpiadas25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Olimpiadas25Application {

	public static void main(String[] args) {
		SpringApplication.run(Olimpiadas25Application.class, args);


		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode("Olimpiadas25");
		System.out.println("Password cifrado: " + hashedPassword);

	}
}
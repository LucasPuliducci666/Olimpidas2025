package com.example.olimpiadas25;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

@SpringBootApplication
public class Olimpiadas25Application {

	public static void main(String[] args) {
		SpringApplication.run(Olimpiadas25Application.class, args);

	}
}
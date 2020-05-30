package com.tripped;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.tripped" })
public class TripApp {

	public static void main(String[] args) {
		SpringApplication.run(TripApp.class, args);
	}
}

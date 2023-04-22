package com.speedxcourier.SpeedX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpeedXApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeedXApplication.class, args);
	}

}

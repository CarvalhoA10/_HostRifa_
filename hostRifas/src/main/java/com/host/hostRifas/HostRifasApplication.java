package com.host.hostRifas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HostRifasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostRifasApplication.class, args);
	}

}

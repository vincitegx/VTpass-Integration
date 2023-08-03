package com.neptunesoftware.vtpassintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class VtpassIntegrationApplication {

	public static void main(String[] args) {
		File logsDirectory = new File("logs");
		if (!logsDirectory.exists()) {
			logsDirectory.mkdirs();
		}
		SpringApplication.run(VtpassIntegrationApplication.class, args);
	}

}
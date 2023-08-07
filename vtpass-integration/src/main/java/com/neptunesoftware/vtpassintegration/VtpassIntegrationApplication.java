package com.neptunesoftware.vtpassintegration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "Neptune Software Group",
						url = "https://neptunesoftwaregroup.com"
				),
				description = "OpenAPI Documentation For VTPass Integration",
				title = "OpenAPI Specification - Neptune",
				version = "1.0"
		),
		servers = {
				@Server(
						description = "Local Env",
						url = "http://localhost:8080"
				)
		}
)
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
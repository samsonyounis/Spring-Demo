package com.example.springdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(/*exclude = { SecurityAutoConfiguration.class }*/)
@OpenAPIDefinition(info = @Info(
		title = "Spring Demo Api documentation",
		description = "this includes the documentation for the models and controllers",
		contact = @Contact(name = "samson",email = "samson@gmail.com",url = "http://localhost:9090/api/getAll"),
version = "1.0.0", license = @License(name = "samix",url = "http://localhost:9090/api/getAll")))
public class SpringDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
}

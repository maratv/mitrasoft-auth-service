package com.example.mitrasoftauthservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Auth service",
        description = "Spring Boot, Security, WebClient Microservice",
        contact = @Contact(
                name = "Marat Vakhitov",
                email = "maratv@gmail.com"
        ),
        version = "0.0.1"
))
@Configuration
public class OpenApiConfig {
}

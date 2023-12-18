package edu.microservices.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API for the Management service users ",
                version = "1.0",
                description = "API that exposes the endpoints for the Users entities solution (User Service)"
            )
        )
public class SwaggerConfig {
    @Value("${app.version}")
    private String appVersion;
}

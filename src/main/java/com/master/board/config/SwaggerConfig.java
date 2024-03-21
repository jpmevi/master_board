package com.master.board.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
        title = "Master Board API",
        version = "1.0.0",
        description = "This is a api to the application master board"
        )
)
public class SwaggerConfig {
}
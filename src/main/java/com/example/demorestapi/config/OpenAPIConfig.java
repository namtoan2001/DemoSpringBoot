package com.example.demorestapi.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OpenApiCustomiser;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://demospringboot-production-7e99.up.railway.app"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info((new Info()
                .title("SpringDoc API Test")
                .description("SpringDoc Simple Application Test")
                .version("1.0")
        ));
    }
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
            operation.addParametersItem(new Parameter().in("header")
                    .name("Authorization")
                    .description("Access token")
                    .required(true)
                    .example("Bearer your_access_token_here"));
        }));
    }
}

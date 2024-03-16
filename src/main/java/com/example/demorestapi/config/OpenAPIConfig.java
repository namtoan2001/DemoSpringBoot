package com.example.demorestapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://demospringboot-production-7e99.up.railway.app"))
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info((new Info()
                .title("SpringDoc API Test")
                .description("SpringDoc Simple Application Test")
                .version("1.0")
        ));
    }
}

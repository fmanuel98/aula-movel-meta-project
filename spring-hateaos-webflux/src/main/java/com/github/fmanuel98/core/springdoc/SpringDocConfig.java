package com.github.fmanuel98.core.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurante Eva & Filhos API")
                        .version("v1")
                        .description("REST API do Restaurante")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Meu GitHub")
                        .url("https://github.com/fmanuel98"));
    }
}

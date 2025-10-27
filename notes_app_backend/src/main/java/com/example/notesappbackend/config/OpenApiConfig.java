package com.example.notesappbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI metadata configuration.
 */
// PUBLIC_INTERFACE
@Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI notesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Simple Notes API")
                        .description("CRUD APIs for managing notes")
                        .version("0.1.0")
                        .contact(new Contact().name("Notes App").email("noreply@example.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addTagsItem(new Tag().name("Notes").description("CRUD operations for notes"))
                .addTagsItem(new Tag().name("Hello Controller").description("Basic endpoints for notesappbackend"))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}

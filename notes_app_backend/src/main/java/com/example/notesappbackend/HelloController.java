package com.example.notesappbackend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Tag(name = "Hello Controller", description = "Basic endpoints for notesappbackend")
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        return "Hello, Spring Boot! Welcome to notesappbackend";
    }



    @GetMapping("/swagger-ui")
    @Operation(summary = "Swagger UI (friendly path)", description = "Redirects to the canonical Swagger UI index")
    public RedirectView swaggerUiRedirect(HttpServletRequest request) {
        String target = UriComponentsBuilder
                .fromHttpRequest(new ServletServerHttpRequest(request))
                .replacePath("/swagger-ui/index.html")
                .replaceQuery(null)
                .build()
                .toUriString();
        RedirectView rv = new RedirectView(target);
        rv.setHttp10Compatible(false);
        return rv;
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: notesappbackend";
    }
}
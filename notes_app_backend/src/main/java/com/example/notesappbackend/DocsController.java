package com.example.notesappbackend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller providing a reliable redirect for API documentation.
 * Maps GET /docs to the Springdoc Swagger UI at /swagger-ui/index.html while preserving
 * the original scheme, host, and port (useful when running behind reverse proxies).
 */
@RestController
@Tag(name = "Documentation", description = "API documentation redirect endpoints")
public class DocsController {

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    @Operation(
            summary = "API Docs redirect",
            description = "Redirects to the Swagger UI index page. This helps avoid static resource conflicts and provides a stable docs URL."
    )
    public RedirectView docs(HttpServletRequest request) {
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
}

# Simple Notes Manager - Backend

Spring Boot backend for a simple notes application providing CRUD REST APIs.

- Framework: Spring Boot 3
- Build tool: Gradle
- Database: H2 (in-memory, auto DDL)
- OpenAPI/Swagger: springdoc-openapi

Run locally using the Gradle wrapper:
- Linux/macOS: `./gradlew bootRun`
- Windows: `gradlew.bat bootRun`

By default the app uses an in-memory H2 database and exposes Swagger UI at `/swagger-ui.html`. A convenience redirect is available at `/docs`.

Health/info:
- GET `/health` returns "OK"
- GET `/api/info` shows basic info
- GET `/docs` redirects to Swagger UI
- OpenAPI JSON: `/api-docs`

Notes API:
- GET `/notes` — returns list of notes (200)
- GET `/notes/{id}` — returns a note (200) or 404 if not found
- POST `/notes` — creates a note (201). Body:
  {
    "title": "Shopping list",
    "content": "- Milk\n- Bread"
  }
- PUT `/notes/{id}` — updates a note (200) or 404. Body same as POST.
- DELETE `/notes/{id}` — deletes a note (204) or 404.

Validation:
- title is required, non-blank, max 255 chars.
- Errors return RFC7807 ProblemDetail with HTTP 400 (validation) or 404 (not found).

Sample curl:
- List notes:
  curl -s https://<host>:3001/notes

- Create a note:
  curl -s -X POST https://<host>:3001/notes \
    -H "Content-Type: application/json" \
    -d '{"title":"First","content":"Hello"}'

- Get by id:
  curl -s https://<host>:3001/notes/1

- Update:
  curl -s -X PUT https://<host>:3001/notes/1 \
    -H "Content-Type: application/json" \
    -d '{"title":"Updated","content":"World"}'

- Delete:
  curl -s -X DELETE https://<host>:3001/notes/1 -i

Configuration notes:
- application.properties is configured for H2 in-memory with JPA ddl-auto=update.
- CORS is allowed via @CrossOrigin on the Notes controller for development. For production, consider a stricter origin whitelist or global CORS config.

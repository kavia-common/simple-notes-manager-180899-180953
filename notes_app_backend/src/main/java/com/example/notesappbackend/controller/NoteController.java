package com.example.notesappbackend.controller;

import com.example.notesappbackend.dto.NoteRequest;
import com.example.notesappbackend.dto.NoteResponse;
import com.example.notesappbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing CRUD endpoints for notes.
 *
 * Endpoints:
 * - GET /notes: List all notes
 * - GET /notes/{id}: Get note by id
 * - POST /notes: Create a new note
 * - PUT /notes/{id}: Update an existing note
 * - DELETE /notes/{id}: Delete a note by id
 */
@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "CRUD operations for notes")
@CrossOrigin(origins = {"*"})
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns a list of all notes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteResponse.class)))
            }
    )
    public List<NoteResponse> listNotes() {
        return service.findAll();
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get note by id",
            description = "Returns a single note by its identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public NoteResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a note",
            description = "Creates a new note. Title is required.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public NoteResponse create(@Valid @RequestBody NoteRequest request) {
        return service.create(request);
    }

    // PUBLIC_INTERFACE
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update a note",
            description = "Updates the title and/or content of an existing note.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public NoteResponse update(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        return service.update(id, request);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a note",
            description = "Deletes a note by its identifier.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package com.example.notesappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating/updating a Note.
 */
// PUBLIC_INTERFACE
@Schema(name = "NoteRequest", description = "Payload to create or update a note")
public class NoteRequest {

    @NotBlank(message = "title is required")
    @Size(max = 255, message = "title must be <= 255 characters")
    @Schema(description = "Title of the note", example = "Shopping List", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Content of the note", example = "- Milk\n- Bread")
    private String content;

    public NoteRequest() {}

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters/Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

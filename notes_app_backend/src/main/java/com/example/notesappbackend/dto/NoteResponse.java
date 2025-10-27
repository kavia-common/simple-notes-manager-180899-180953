package com.example.notesappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

/**
 * DTO returned by Note endpoints.
 */
// PUBLIC_INTERFACE
@Schema(name = "NoteResponse", description = "Note representation returned by the API")
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    @Schema(description = "Title of the note", example = "Shopping List")
    private String title;

    @Schema(description = "Content of the note", example = "- Milk\n- Bread")
    private String content;

    @Schema(description = "Creation timestamp in ISO-8601 format")
    private OffsetDateTime createdAt;

    @Schema(description = "Last update timestamp in ISO-8601 format")
    private OffsetDateTime updatedAt;

    public NoteResponse() {}

    public NoteResponse(Long id, String title, String content, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}

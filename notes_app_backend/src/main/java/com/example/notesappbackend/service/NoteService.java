package com.example.notesappbackend.service;

import com.example.notesappbackend.dto.NoteRequest;
import com.example.notesappbackend.dto.NoteResponse;
import com.example.notesappbackend.entity.Note;
import com.example.notesappbackend.exception.NotFoundException;
import com.example.notesappbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic for managing notes.
 */
// PUBLIC_INTERFACE
@Service
@Transactional
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public List<NoteResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // PUBLIC_INTERFACE
    public NoteResponse findById(Long id) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        return toResponse(note);
    }

    // PUBLIC_INTERFACE
    public NoteResponse create(NoteRequest request) {
        Note note = new Note(request.getTitle(), request.getContent());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public NoteResponse update(Long id, NoteRequest request) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        Note saved = repository.save(note);
        return toResponse(saved);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}

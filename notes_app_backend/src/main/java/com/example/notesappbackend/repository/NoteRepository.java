package com.example.notesappbackend.repository;

import com.example.notesappbackend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Note entity.
 */
// PUBLIC_INTERFACE
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}

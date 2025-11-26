package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}

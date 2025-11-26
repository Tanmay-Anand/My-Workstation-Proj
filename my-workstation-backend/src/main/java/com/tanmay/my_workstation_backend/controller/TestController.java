package com.tanmay.my_workstation_backend.controller;

import com.tanmay.my_workstation_backend.domain.Note;
import com.tanmay.my_workstation_backend.repository.NoteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final NoteRepository noteRepo;

    public TestController(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }
}

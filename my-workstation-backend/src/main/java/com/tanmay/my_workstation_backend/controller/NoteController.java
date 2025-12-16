package com.tanmay.my_workstation_backend.controller;

import com.tanmay.my_workstation_backend.dto.NoteRequest;
import com.tanmay.my_workstation_backend.dto.NoteResponse;
import com.tanmay.my_workstation_backend.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor

public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest req) {
        NoteResponse resp = noteService.createNote(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    //List Notes
    @GetMapping
    public ResponseEntity<Page<NoteResponse>> list(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<NoteResponse> page = noteService.listNotes(q, pageable);
        return ResponseEntity.ok(page);
    }

    //Get a single note
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> get(@PathVariable Long id) {
        NoteResponse resp = noteService.getNote(id);
        return ResponseEntity.ok(resp);
    }

    //Update a note
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(@PathVariable Long id, @Valid @RequestBody NoteRequest req) {
        NoteResponse resp = noteService.updateNote(id, req);
        return ResponseEntity.ok(resp);
    }

    //Delete a note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}


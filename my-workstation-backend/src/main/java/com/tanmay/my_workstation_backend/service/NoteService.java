package com.tanmay.my_workstation_backend.service;

import com.tanmay.my_workstation_backend.domain.Note;
import com.tanmay.my_workstation_backend.domain.User;
import com.tanmay.my_workstation_backend.dto.NoteRequest;
import com.tanmay.my_workstation_backend.dto.NoteResponse;
import com.tanmay.my_workstation_backend.exception.ResourceNotFoundException;
import com.tanmay.my_workstation_backend.mapper.NoteMapper;
import com.tanmay.my_workstation_backend.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    @Transactional
    public NoteResponse createNote(NoteRequest req) {
        User user = userService.getCurrentUser();
        Note note = NoteMapper.toEntity(req, user);
        Note saved = noteRepository.save(note);
        return NoteMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<NoteResponse> listNotes(String q, Pageable pageable) {
        Long userId = userService.getCurrentUserId();
        Page<Note> page;
        if (q == null || q.isBlank()) {
            page = noteRepository.findByUserId(userId, pageable);
        } else {
            page = noteRepository.searchByUserAndQuery(userId, q, pageable);
        }
        return page.map(NoteMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public NoteResponse getNote(Long id) {
        Long userId = userService.getCurrentUserId();
        Note n = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", id));
        if (!n.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Note", id); // hide presence to other users
        }
        return NoteMapper.toResponse(n);
    }

    @Transactional
    public NoteResponse updateNote(Long id, NoteRequest req) {
        Long userId = userService.getCurrentUserId();
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", id));
        if (!note.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Note", id);
        }
        NoteMapper.updateEntity(note, req);
        Note saved = noteRepository.save(note);
        return NoteMapper.toResponse(saved);
    }

    @Transactional
    public void deleteNote(Long id) {
        Long userId = userService.getCurrentUserId();
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", id));
        if (!note.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Note", id);
        }
        noteRepository.delete(note);
    }
}

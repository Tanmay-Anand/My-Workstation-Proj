package com.tanmay.my_workstation_backend.mapper;

import com.tanmay.my_workstation_backend.domain.Note;
import com.tanmay.my_workstation_backend.domain.User;
import com.tanmay.my_workstation_backend.dto.NoteRequest;
import com.tanmay.my_workstation_backend.dto.NoteResponse;

public class NoteMapper {

    public static Note toEntity(NoteRequest req, User user) {
        Note n = new Note();
        n.setUser(user);
        n.setTitle(req.getTitle());
        n.setContent(req.getContent());

        if (req.getPinned() != null) n.setPinned(req.getPinned());
        if (req.getArchived() != null) n.setArchived(req.getArchived());
        if (req.getTags() != null) n.setTags(req.getTags());

        return n;
    }

    public static void updateEntity(Note note, NoteRequest req) {
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());

        if (req.getPinned() != null) note.setPinned(req.getPinned());
        if (req.getArchived() != null) note.setArchived(req.getArchived());
        if (req.getTags() != null) note.setTags(req.getTags());
    }

    public static NoteResponse toResponse(Note n) {
        NoteResponse r = new NoteResponse();
        r.setId(n.getId());
        r.setTitle(n.getTitle());
        r.setContent(n.getContent());
        r.setPinned(n.isPinned());
        r.setArchived(n.isArchived());
        r.setTags(n.getTags());
        r.setCreatedAt(n.getCreatedAt());
        r.setUpdatedAt(n.getUpdatedAt());
        return r;
    }
}

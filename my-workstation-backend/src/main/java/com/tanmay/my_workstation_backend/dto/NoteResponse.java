package com.tanmay.my_workstation_backend.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class NoteResponse {

    private Long id;

    private String title;
    private String content;

    private boolean pinned;
    private boolean archived;

    private Set<String> tags;

    private Instant createdAt;
    private Instant updatedAt;
}

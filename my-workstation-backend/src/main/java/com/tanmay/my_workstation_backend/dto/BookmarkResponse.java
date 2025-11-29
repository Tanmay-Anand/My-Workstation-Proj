package com.tanmay.my_workstation_backend.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class BookmarkResponse {

    private Long id;

    private String url;
    private String title;
    private String description;
    private String category;

    private Set<String> tags;

    private Instant createdAt;
    private Instant updatedAt;
}

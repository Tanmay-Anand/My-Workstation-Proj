package com.tanmay.my_workstation_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class NoteRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;

    @Size(max = 10_000, message = "Content too long")
    private String content;

    private Boolean pinned;
    private Boolean archived;

    private Set<
            @Size(max = 30, message = "Tag cannot exceed 30 characters")
                    String
            > tags;
}

package com.tanmay.my_workstation_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class BookmarkRequest {

    @NotBlank(message = "URL cannot be empty")
    @Pattern(
            regexp = "^(https?://).*",
            message = "URL must start with http:// or https://"
    )
    private String url;

    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    private Set<
            @Size(max = 30, message = "Tag cannot exceed 30 characters")
                    String
            > tags;
}
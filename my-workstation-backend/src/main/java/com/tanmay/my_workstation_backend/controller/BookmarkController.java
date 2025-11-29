package com.tanmay.my_workstation_backend.controller;

import com.tanmay.my_workstation_backend.dto.BookmarkRequest;
import com.tanmay.my_workstation_backend.dto.BookmarkResponse;
import com.tanmay.my_workstation_backend.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<BookmarkResponse> create(@Valid @RequestBody BookmarkRequest req) {
        BookmarkResponse resp = bookmarkService.createBookmark(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<Page<BookmarkResponse>> list(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "tags", required = false) String tagsCsv,
            @PageableDefault(size = 20) Pageable pageable) {

        Set<String> tags = null;
        if (tagsCsv != null && !tagsCsv.isBlank()) {
            tags = Arrays.stream(tagsCsv.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toCollection(HashSet::new));
        }

        Page<BookmarkResponse> page = bookmarkService.listBookmarks(q, category, tags, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponse> get(@PathVariable Long id) {
        BookmarkResponse resp = bookmarkService.getBookmark(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody BookmarkRequest req) {
        BookmarkResponse resp = bookmarkService.updateBookmark(id, req);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.noContent().build();
    }
}

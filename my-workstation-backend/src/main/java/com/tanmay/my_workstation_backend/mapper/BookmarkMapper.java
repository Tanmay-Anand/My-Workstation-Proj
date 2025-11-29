package com.tanmay.my_workstation_backend.mapper;

import com.tanmay.my_workstation_backend.domain.Bookmark;
import com.tanmay.my_workstation_backend.domain.User;
import com.tanmay.my_workstation_backend.dto.BookmarkRequest;
import com.tanmay.my_workstation_backend.dto.BookmarkResponse;

public class BookmarkMapper {

    public static Bookmark toEntity(BookmarkRequest req, User user) {
        Bookmark b = new Bookmark();
        b.setUser(user);
        b.setUrl(req.getUrl());
        b.setTitle(req.getTitle());
        b.setDescription(req.getDescription());
        b.setCategory(req.getCategory());
        if (req.getTags() != null) b.setTags(req.getTags());
        return b;
    }

    public static void updateEntity(Bookmark b, BookmarkRequest req) {
        b.setUrl(req.getUrl());
        b.setTitle(req.getTitle());
        b.setDescription(req.getDescription());
        b.setCategory(req.getCategory());

        if (req.getTags() != null) b.setTags(req.getTags());
    }

    public static BookmarkResponse toResponse(Bookmark b) {
        BookmarkResponse r = new BookmarkResponse();
        r.setId(b.getId());
        r.setUrl(b.getUrl());
        r.setTitle(b.getTitle());
        r.setDescription(b.getDescription());
        r.setCategory(b.getCategory());
        r.setTags(b.getTags());
        r.setCreatedAt(b.getCreatedAt());
        r.setUpdatedAt(b.getUpdatedAt());
        return r;
    }
}

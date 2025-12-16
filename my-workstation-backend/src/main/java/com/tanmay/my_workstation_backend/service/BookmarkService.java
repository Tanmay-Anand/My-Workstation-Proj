package com.tanmay.my_workstation_backend.service;

import com.tanmay.my_workstation_backend.domain.Bookmark;
import com.tanmay.my_workstation_backend.domain.User;
import com.tanmay.my_workstation_backend.dto.BookmarkRequest;
import com.tanmay.my_workstation_backend.dto.BookmarkResponse;
import com.tanmay.my_workstation_backend.exception.ResourceNotFoundException;
import com.tanmay.my_workstation_backend.mapper.BookmarkMapper;
import com.tanmay.my_workstation_backend.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;

    @Transactional
    public BookmarkResponse createBookmark(BookmarkRequest req) {
        User user = userService.getCurrentUser();
        if (bookmarkRepository.existsByUserIdAndUrl(user.getId(), req.getUrl())) {
        }
        Bookmark b = BookmarkMapper.toEntity(req, user);
        Bookmark saved = bookmarkRepository.save(b);
        return BookmarkMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<BookmarkResponse> listBookmarks(String q, String category, Set<String> tags, Pageable pageable) {
        Long userId = userService.getCurrentUserId();
        Page<Bookmark> page;

        if (tags != null && !tags.isEmpty()) {
            page = bookmarkRepository.findByTags(userId, tags, pageable);
        } else if (category != null && !category.isBlank()) {
            page = bookmarkRepository.findByUserIdAndCategory(userId, category, pageable);
        } else if (q != null && !q.isBlank()) {
            page = bookmarkRepository.searchByTitle(userId, q, pageable);
        } else {
            page = bookmarkRepository.findByUserId(userId, pageable);
        }

        return page.map(BookmarkMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public BookmarkResponse getBookmark(Long id) {
        Long userId = userService.getCurrentUserId();
        Bookmark b = bookmarkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark", id));
        if (!b.getUser().getId().equals(userId)) throw new ResourceNotFoundException("Bookmark", id);
        return BookmarkMapper.toResponse(b);
    }

    @Transactional
    public BookmarkResponse updateBookmark(Long id, BookmarkRequest req) {
        Long userId = userService.getCurrentUserId();
        Bookmark b = bookmarkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark", id));
        if (!b.getUser().getId().equals(userId)) throw new ResourceNotFoundException("Bookmark", id);

        BookmarkMapper.updateEntity(b, req);
        Bookmark saved = bookmarkRepository.save(b);
        return BookmarkMapper.toResponse(saved);
    }

    @Transactional
    public void deleteBookmark(Long id) {
        Long userId = userService.getCurrentUserId();
        Bookmark b = bookmarkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark", id));
        if (!b.getUser().getId().equals(userId)) throw new ResourceNotFoundException("Bookmark", id);
        bookmarkRepository.delete(b);
    }
}

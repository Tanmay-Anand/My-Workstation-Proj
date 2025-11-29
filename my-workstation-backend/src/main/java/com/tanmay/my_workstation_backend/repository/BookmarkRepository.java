package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findByUserId(Long userId, Pageable pageable);

    // Search by title
    @Query("""
        SELECT b FROM Bookmark b
        WHERE b.user.id = :userId
        AND (LOWER(b.title) LIKE LOWER(CONCAT('%', :q, '%')))
        """)
    Page<Bookmark> searchByTitle(
            @Param("userId") Long userId,
            @Param("q") String q,
            Pageable pageable
    );

    // Filter by category
    Page<Bookmark> findByUserIdAndCategory(
            Long userId,
            String category,
            Pageable pageable
    );

    // Filter by tag list
    @Query("""
        SELECT DISTINCT b FROM Bookmark b
        JOIN b.tags t
        WHERE b.user.id = :userId
        AND t IN :tags
        """)
    Page<Bookmark> findByTags(
            @Param("userId") Long userId,
            @Param("tags") Set<String> tags,
            Pageable pageable
    );

    // URL uniqueness check within same user
    boolean existsByUserIdAndUrl(Long userId, String url);
}

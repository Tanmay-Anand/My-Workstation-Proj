package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Basic list
    Page<Note> findByUserId(Long userId, Pageable pageable);

    // Search title or content
    @Query("""
        SELECT n FROM Note n
        WHERE n.user.id = :userId
        AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :q, '%'))
             OR LOWER(n.content) LIKE LOWER(CONCAT('%', :q, '%')))
        """)
    Page<Note> searchByUserAndQuery(
            @Param("userId") Long userId,
            @Param("q") String q,
            Pageable pageable
    );

    // Filter by tags
    @Query("""
        SELECT DISTINCT n FROM Note n 
        JOIN n.tags t
        WHERE n.user.id = :userId
        AND t IN :tags
        """)
    Page<Note> findByTags(
            @Param("userId") Long userId,
            @Param("tags") Set<String> tags,
            Pageable pageable
    );

    // Filter by pinned or archived
    Page<Note> findByUserIdAndPinned(Long userId, boolean pinned, Pageable pageable);
    //SELECT * FROM note WHERE user_id = ? AND pinned = ? LIMIT ...

    Page<Note> findByUserIdAndArchived(Long userId, boolean archived, Pageable pageable);
}


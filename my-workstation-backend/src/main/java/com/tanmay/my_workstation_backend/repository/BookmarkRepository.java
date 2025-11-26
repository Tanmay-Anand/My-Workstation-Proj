package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
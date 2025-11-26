package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
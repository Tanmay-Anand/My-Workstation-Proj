package com.tanmay.my_workstation_backend.repository;

import com.tanmay.my_workstation_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

package com.tanmay.my_workstation_backend.service;

import com.tanmay.my_workstation_backend.domain.User;
import com.tanmay.my_workstation_backend.exception.ResourceNotFoundException;
import com.tanmay.my_workstation_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Resolve currently authenticated user entity.
     * Assumes Authentication#getName() returns username.
     */
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new ResourceNotFoundException("User", "anonymous");
        }
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", username));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}

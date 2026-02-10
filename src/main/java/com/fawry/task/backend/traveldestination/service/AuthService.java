package com.fawry.task.backend.traveldestination.service;

import com.fawry.task.backend.traveldestination.dto.RegisterRequest;
import com.fawry.task.backend.traveldestination.model.Role;
import com.fawry.task.backend.traveldestination.model.User;
import com.fawry.task.backend.traveldestination.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if(userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        var user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.valueOf(request.role().toUpperCase()))
                .build();
        userRepository.save(user);
    }

}

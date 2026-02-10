package com.fawry.task.backend.traveldestination.service;

import com.fawry.task.backend.traveldestination.dto.LoginRequest;
import com.fawry.task.backend.traveldestination.dto.LoginResponse;
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

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Username not found"));
        System.out.println("Input password: " + request.password());
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Matches: " + passwordEncoder.matches(request.password(), user.getPassword()));

        return new LoginResponse("Fake token");

    }

}

package com.fawry.task.backend.traveldestination.service;

import com.fawry.task.backend.traveldestination.dto.LoginRequest;
import com.fawry.task.backend.traveldestination.dto.LoginResponse;
import com.fawry.task.backend.traveldestination.dto.RegisterRequest;
import com.fawry.task.backend.traveldestination.dto.RegisterResponse;
import com.fawry.task.backend.traveldestination.model.Role;
import com.fawry.task.backend.traveldestination.model.User;
import com.fawry.task.backend.traveldestination.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        var user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.valueOf(request.role().toUpperCase()))
                .build();
        userRepository.save(user);

        final String jwt = jwtService.generateToken(user);
        return new RegisterResponse(jwt);
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                ));
        final var userDetails = userDetailsService.loadUserByUsername(request.username());
        final String jwt = jwtService.generateToken(userDetails);

        return new LoginResponse(jwt);

    }

}

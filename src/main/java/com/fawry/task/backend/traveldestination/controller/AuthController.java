package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.*;
import com.fawry.task.backend.traveldestination.service.AuthService;
import com.fawry.task.backend.traveldestination.service.JwtService;
import com.fawry.task.backend.traveldestination.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody RegisterRequest request) {
        var token = authService.register(request);

        return ResponseEntity.ok(ApiResponse.success(token, "User registered successfully"));

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse token = authService.login(request);

        return ResponseEntity.ok(ApiResponse.success(token, "User registered successfully"));

    }
}

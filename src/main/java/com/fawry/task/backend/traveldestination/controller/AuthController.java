package com.fawry.task.backend.traveldestination.controller;

import com.fawry.task.backend.traveldestination.dto.ApiResponse;
import com.fawry.task.backend.traveldestination.dto.LoginRequest;
import com.fawry.task.backend.traveldestination.dto.LoginResponse;
import com.fawry.task.backend.traveldestination.dto.RegisterRequest;
import com.fawry.task.backend.traveldestination.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {
        authService.register(request);

        return ResponseEntity.ok(ApiResponse.success(null, "User registered successfully"));

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse token = authService.login(request);

        return ResponseEntity.ok(ApiResponse.success(token, "User registered successfully"));

    }
}

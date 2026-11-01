package com.iu.backend.controller;

import com.iu.backend.dto.AuthResponse; import com.iu.backend.dto.LoginRequest; import com.iu.backend.dto.RegisterRequest; import com.iu.backend.service.AuthService; import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; import org.springframework.web.bind.annotation.\*;

@RestController 
@RequestMapping ("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

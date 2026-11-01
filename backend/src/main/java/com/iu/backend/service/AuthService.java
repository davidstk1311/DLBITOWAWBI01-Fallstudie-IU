package com.iu.backend.service;

import com.iu.backend.config.JwtTokenProvider; 
import com.iu.backend.dto.AuthResponse; 
import com.iu.backend.dto.LoginRequest; 
import com.iu.backend.dto.RegisterRequest; 
import com.iu.backend.model.Role; 
import com.iu.backend.model.User; 
import com.iu.backend.repository.UserRepository; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

@Service 
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username ist bereits vergeben");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return "User registriert.";
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole().name());
        
        return new AuthResponse(token);
    }
}

package com.Hospital.Management.backend.service;

import com.Hospital.Management.backend.dto.AuthResponse;
import com.Hospital.Management.backend.dto.LoginRequest;
import com.Hospital.Management.backend.dto.RegisterRequest;
import com.Hospital.Management.backend.entity.User;
import com.Hospital.Management.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        String role = (request.getRole() == null || request.getRole().trim().isEmpty()) ? "PATIENT" : request.getRole().toUpperCase();
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getName(), request.getEmail(), encodedPassword, role);
        User savedUser = userRepository.save(user);

        return new AuthResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole(), "User registered successfully");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()) && !request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials for email: " + request.getEmail());
        }

        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), "Login successful");
    }
}
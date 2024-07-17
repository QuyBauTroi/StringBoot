package com.example.movieapp.service;

import com.example.movieapp.entity.User;
import com.example.movieapp.model.enums.UserRole;
import com.example.movieapp.model.request.RegisterRequest;
import com.example.movieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        String name = request.getName();
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password does not match");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setAvatar("https://placehold.co/600x400/"+ "/FFF" + "?text=" + String.valueOf(name.charAt(0)).toUpperCase());
        user.setRole(Enum.valueOf(UserRole.class, "USER"));
        userRepository.save(user);
    }
}
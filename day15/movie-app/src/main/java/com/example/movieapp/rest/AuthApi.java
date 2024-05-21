package com.example.movieapp.rest;

import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.model.request.LoginRequest;
import com.example.movieapp.model.request.UpdatePasswordRequest;
import com.example.movieapp.model.request.UpdateProfileUserRequest;
import com.example.movieapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        authService.login(request);
        return ResponseEntity.ok("Login successfully");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok("Logout successfully");
    }
    @PostMapping("/update-name")
    public ResponseEntity<?> updateName(@Valid @RequestBody UpdateProfileUserRequest request) {
        try {
            authService.updateName(request.getName());
            return ResponseEntity.ok("Name updated successfully");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        try {
            authService.updatePassword(request);
            return ResponseEntity.ok("Password updated successfully");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

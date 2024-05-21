package com.example.movieapp.service;

import com.example.movieapp.entity.User;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.model.request.LoginRequest;
import com.example.movieapp.model.request.UpdatePasswordRequest;
import com.example.movieapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        log.info("request: {}", request);
        // Kiem tra xem user co ton tai trong database khong
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email incorrect"));

        // Kiểm tra xem password có khớp với password trong database không
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Password incorrect");
        }

        // Lưu thông tin user vào trong session để sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }
    public void logout() {
        session.setAttribute("currentUser", null);
        session.invalidate();
        log.info("User logged out successfully");
    }
    public void updateName(String newName) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new BadRequestException("User not logged in");
        }
        currentUser.setName(newName);
        userRepository.save(currentUser);

        session.setAttribute("currentUser", currentUser);
        log.info("Name updated successfully: {}", newName);
    }
    public void updatePassword(UpdatePasswordRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new BadRequestException("User not logged in");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("New password and confirm password do not match");
        }
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(currentUser);
        session.setAttribute("currentUser", currentUser);
        log.info("Password updated successfully");
    }
}

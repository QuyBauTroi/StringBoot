package com.example.movieapp.rest;

import com.example.movieapp.model.request.RegisterRequest;
import com.example.movieapp.repository.UserRepository;
import com.example.movieapp.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterApi {
    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> register(@Valid  @RequestBody RegisterRequest request){
        registerService.register(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED); //tra ve 201
    }
}

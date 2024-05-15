package com.example.demo.rest;

import com.example.demo.entities.Review;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class MovieApi {
    private final ReviewService reviewService;

    //tao review
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request) {
        Review review = reviewService.createReview(request);
        return  new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // cap nhat review
    @PutMapping{"/{id}"}
}

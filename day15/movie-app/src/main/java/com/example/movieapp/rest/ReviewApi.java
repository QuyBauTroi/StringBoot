package com.example.movieapp.rest;

import com.example.movieapp.entity.Review;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.request.UpsertReviewRequest;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    // Tạo review
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request) {
        Review review = reviewService.createReview(request);
        return new ResponseEntity<>(review, HttpStatus.CREATED); // 201
    }

    // Cập nhật review
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable Integer id) {
        Review review = reviewService.updateReview(id, request);
        return ResponseEntity.ok(review); // 200
    }

    // Xóa review
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu xóa thành công
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found nếu không tìm thấy review
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trả về 400 Bad Request nếu có lỗi xóa
        }
    }
}

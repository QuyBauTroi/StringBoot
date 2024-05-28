package com.example.movieapp.rest;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.model.request.UpsertMovieRequest;
import com.example.movieapp.model.request.UpsertReviewRequest;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody UpsertMovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED); // 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build(); //204
    }

}

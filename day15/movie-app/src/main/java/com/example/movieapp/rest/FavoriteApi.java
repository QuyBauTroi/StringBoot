package com.example.movieapp.rest;

import com.example.movieapp.entity.Favorite;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.request.CreateFavoriteRequest;
import com.example.movieapp.repository.FavoriteRepository;
import com.example.movieapp.service.FavouriteService;
import com.example.movieapp.service.MovieService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavouriteService favoriteService;
    private final HttpSession session;
    private final FavoriteRepository favoriteRepository;


    @PostMapping
    public ResponseEntity<?> createFavorite(@Valid @RequestBody CreateFavoriteRequest request) {
        Favorite favorite = favoriteService.createFavorite(request);
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Integer id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/check/{userId}/{movieId}")
    public ResponseEntity<?> checkFavorite(@PathVariable Integer userId, @PathVariable Integer movieId) {
        boolean isFavorite = favoriteService.isFavorite(userId, movieId);
        return ResponseEntity.ok().body(Map.of("isFavorite", isFavorite));
    }
}

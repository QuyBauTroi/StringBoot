package com.example.movieapp.rest;

import com.example.movieapp.entity.Favorite;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.model.request.CreateFavoriteRequest;
import com.example.movieapp.service.FavouriteService;
import com.example.movieapp.service.MovieService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavouriteService favoriteService;
    private final MovieService movieService;
    private HttpSession session;

    @PostMapping("/api/favorites")
    public ResponseEntity<Favorite> addFavorite(@RequestBody CreateFavoriteRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Movie movie = movieService.getMovieById(request.getMovieId());
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Favorite favorite = favoriteService.createFavorite(currentUser, movie);
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/api/favorites/{id}")
    public ResponseEntity<List<Favorite>> removeFavorite(@PathVariable Integer id) {
        favoriteService.deleteFavorite(id);
        List<Favorite> favoriteMovies = favoriteService.getAllFavorites(); // Assuming you have a method to get all favorites
        return ResponseEntity.ok(favoriteMovies);
    }
}

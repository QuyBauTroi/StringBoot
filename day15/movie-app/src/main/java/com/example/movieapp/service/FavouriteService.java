package com.example.movieapp.service;

import com.example.movieapp.entity.Favorite;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.request.CreateFavoriteRequest;
import com.example.movieapp.repository.FavoriteRepository;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService {
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;
    private final HttpSession httpSession;



    public Favorite createFavorite(CreateFavoriteRequest request) {
        User user = (User) httpSession.getAttribute("currentUser");

        if (favoriteRepository.existsByUserIdAndMovieId(user.getId(), request.getMovieId())) {
            throw new RuntimeException("This movie is already in your favorites");
        }

        // Kiểm tra xem movie có tồn tại hay không
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        // Tạo yêu thích
        Favorite favorite = Favorite.builder()
                .createdAt(LocalDateTime.now())
                .movie(movie)
                .user(user).build();
        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Integer movieId) {
        // Kiểm tra user này có tồn tại hay ko
        User user = (User) httpSession.getAttribute("currentUser");

        // Kiểm tra yêu thích xem tồn tại ko
        Favorite favorite = favoriteRepository.findByMovieIdAndUserId(movieId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }

    public List<Favorite> getFavoritesByUser(Integer userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public boolean isFavorite(Integer userId, Integer movieId) {
        return favoriteRepository.existsByUserIdAndMovieId(userId, movieId);
    }
}
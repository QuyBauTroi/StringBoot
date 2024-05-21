package com.example.movieapp.service;

import com.example.movieapp.entity.Favorite;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.model.request.CreateFavoriteRequest;
import com.example.movieapp.repository.FavoriteRepository;
import com.example.movieapp.repository.MovieRepository;
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
    private final HttpSession session;


    public List<Favorite> getFavoritesByUser(Integer userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite createFavorite(User user, Movie movie) {
        Favorite favorite = Favorite.builder()
                .createdAt(LocalDateTime.now())
                .user(user)
                .movie(movie)
                .build();
        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Integer favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }
}
package com.example.movieapp.repository;

import com.example.movieapp.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
        List<Favorite> findByUserId(Integer userId);
        boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
        Optional<Favorite> findByMovieIdAndUserId(Integer movieId, Integer userId);


}
package com.example.demo.repository;

import com.example.demo.entities.Movie;
import com.example.demo.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //tìm kiếm
    List<Movie> findByName(String name);

    List<Movie> findByType(MovieType type, Sort sort);

    List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status);
    //đếm số lượng


    List<Movie> findByStatus(Boolean status);

    //Phân trang
   Page<Movie> findByStatus(boolean status, Pageable pageable);
   Page<Movie> findByTypeAndStatus (MovieType type,Boolean status, Pageable pageable);

   List<Movie> findByStatusOrderByRatingDesc(Boolean status);

    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean status);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE g.name = ?1 AND m.status=true AND m.id NOT IN ?2 ORDER BY m.rating DESC")
    List<Movie> findByGenreNameOrderByRatingDescExcludingMovieId(String genreName, Integer excludedMovieId);

    Optional<Movie> findById(Integer id);

    @Query("SELECT DISTINCT m FROM Movie m WHERE m.createdAt BETWEEN ?1 AND ?2")
    List<Movie> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

}

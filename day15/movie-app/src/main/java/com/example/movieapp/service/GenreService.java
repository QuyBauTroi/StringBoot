package com.example.movieapp.service;

import com.example.movieapp.entity.Genre;
import com.example.movieapp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> getAllGenreById(List<Integer> ids) {
        return genreRepository.findAllById(ids);
    }
}
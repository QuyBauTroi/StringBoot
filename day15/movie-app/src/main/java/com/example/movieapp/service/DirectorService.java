package com.example.movieapp.service;

import com.example.movieapp.entity.Director;
import com.example.movieapp.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public List<Director> getAllDirectorById(List<Integer> ids) {
        return directorRepository.findAllById(ids);
    }
}
package com.example.demo.entities;

import com.example.demo.model.MovieType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String poster;
    @Column(nullable = false)
    String name;
    String slug;
    @Column(nullable = false)
    Integer releaseYear;
    String description;
    Double rating;
    MovieType type;
    Boolean status;
    String trailer;

    LocalDate createdAt;
    LocalDate updatedAt;

}

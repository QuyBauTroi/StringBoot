package com.example.movieapp.service;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.request.UpsertMovieRequest;
import com.example.movieapp.repository.CountryRepository;
import com.example.movieapp.repository.MovieRepository;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private final ActorService actorService;
    private final DirectorService directorService;
private final GenreService genreService;


    public List<Movie> getMoviesByType(MovieType movieType, Boolean status, Sort sort) {
        return movieRepository.findByTypeAndStatus(movieType, status, sort);
    }

    public Page<Movie> getMoviesByType(MovieType movieType, Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(movieType, status, pageRequest);
    }

    public Page<Movie> getHotMovies(Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("rating").descending());
        return movieRepository.findByStatus(status, pageRequest);
    }

    public Movie getMovie(Integer id, String slug, Boolean status) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, status).orElse(null);
    }

    public List<Movie> getRelatedMovies(Integer id, MovieType type, Boolean status, Integer size) {
        return movieRepository
                .findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescCreatedAtDesc(type, status, 5.0, id)
                .stream()
                .limit(size)
                .toList();
    }

    public Movie getMovieById(Integer id) {
        if (movieRepository.findById(id).isPresent()) {
            return movieRepository.findById(id).get();
        }
        return null;
    }
    public List<Movie> getAllMovie() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
    public void deleteMovie(Integer id) {
        //Kiểm tra movie có tồn tại hay không
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie not found"));

        movieRepository.delete(movie);
    }
    public Movie createMovie(UpsertMovieRequest request) {
        Slugify slugify= Slugify.builder().build();
        Movie movie = Movie.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .type(request.getMovieType())
                .status(request.getStatus())
                .trailer(request.getTrailer())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .country(countryService.getCountryById(request.getCountryId()))
                .actors(actorService.getAllActorById(request.getActorIds()))
                .directors(directorService.getAllDirectorById(request.getDirectorIds()))
                .genres(genreService.getAllGenreById(request.getGenreIds()))
                .build();
        movieRepository.save(movie);
        return movie;
    }
}

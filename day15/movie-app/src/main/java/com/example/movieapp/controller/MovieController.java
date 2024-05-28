package com.example.movieapp.controller;

import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.*;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class MovieController {
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final MovieService movieService;


    @GetMapping
    public String getIndexPage(Model model) {
        // Lấy tất cả bài viết sắp xếp theo created giảm dần
        model.addAttribute("movies", movieService.getAllMovie());
        return "admin/movie/index";
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("movies",movieService.getMovieById(id));
        return "admin/movie/detail";
    }


    // Tạo movie (template)
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        // Trả về ds quốc gia, đạo diễn, diễn viên, thể loại, loại phim
        // TODO: Refactor theo controller - service - repo
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movieTypes", MovieType.values());
        return "admin/movie/create";
    }

}

package com.example.controllerWithFilters.controller;

import com.example.controllerWithFilters.model.Movie;
import com.example.controllerWithFilters.model.enums.GenreEnum;
import com.example.controllerWithFilters.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public Page<Movie> getAllMoviesByFilters(@RequestParam(name = "title", required = false) String title,
                                             @RequestParam(name = "description", required = false) String description,
                                             @RequestParam(name = "genre", required = false) List<GenreEnum> genreList,
                                             @RequestParam(name = "minRating", required = false) Float minRating,
                                             @RequestParam(name = "maxRating", required = false) Float maxRating,
                                             @RequestParam(name = "sort", defaultValue = "releaseDate,desc", required = false) String[] sortList,
                                             @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size
                                             ){
        return movieService.getAllMoviesByFilter(title, description, genreList, minRating, maxRating, sortList, page, size);
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable UUID id){
        return movieService.getMovieById(id);
    }

    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @PutMapping("/movies/{id}")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable UUID id){
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable UUID id){
        movieService.deleteMovie(id);
    }
}

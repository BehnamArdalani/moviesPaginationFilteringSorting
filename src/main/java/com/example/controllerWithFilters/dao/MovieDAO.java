package com.example.controllerWithFilters.dao;

import com.example.controllerWithFilters.model.Movie;
import com.example.controllerWithFilters.model.enums.GenreEnum;
import com.example.controllerWithFilters.repository.MovieCriteriaRepository;
import com.example.controllerWithFilters.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MovieDAO {

    private final MovieRepository movieRepository;
    private final MovieCriteriaRepository movieCriteriaRepository;
    @Autowired
    public MovieDAO(MovieRepository movieRepository, MovieCriteriaRepository movieCriteriaRepository) {
        this.movieRepository = movieRepository;
        this.movieCriteriaRepository = movieCriteriaRepository;
    }

    public Movie getMovieById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getAllMoviesByFilter(String title, String description, List<GenreEnum> genreList, Float minRating, Float maxRating, String[] sortList, int page, int size) {
        return movieCriteriaRepository.getAllMoviesByFilter(title, description, genreList, minRating, maxRating, sortList, page, size);
    }

    public long getMoviesCount(String title, String description, List<GenreEnum> genreList, Float minRating, Float maxRating) {
        return movieCriteriaRepository.getMoviesCount(title, description, genreList, minRating, maxRating);
    }
}

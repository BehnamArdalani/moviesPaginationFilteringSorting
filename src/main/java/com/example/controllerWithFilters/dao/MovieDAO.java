package com.example.controllerWithFilters.dao;

import com.example.controllerWithFilters.model.Movie;
import com.example.controllerWithFilters.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MovieDAO {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieDAO(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}

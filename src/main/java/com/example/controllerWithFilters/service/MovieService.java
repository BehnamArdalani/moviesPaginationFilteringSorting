package com.example.controllerWithFilters.service;

import com.example.controllerWithFilters.dao.MovieDAO;
import com.example.controllerWithFilters.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieDAO movieDAO;

    @Autowired
    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public Movie getMovieById(UUID id){
        return movieDAO.getMovieById(id);
    }

    public List<Movie> getAllMovies(){
        return movieDAO.getAllMovies();
    }
}

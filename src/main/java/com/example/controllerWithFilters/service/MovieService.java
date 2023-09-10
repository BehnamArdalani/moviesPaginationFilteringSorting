package com.example.controllerWithFilters.service;

import com.example.controllerWithFilters.dao.MovieDAO;
import com.example.controllerWithFilters.model.Movie;
import com.example.controllerWithFilters.model.enums.GenreEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    public Movie getMovieById(UUID id) {
        return movieDAO.getMovieById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieDAO.save(movie);
    }

    public Movie updateMovie(Movie movie, UUID id) {
        Movie foundMovie = getMovieById(id);
        if (foundMovie == null) {
            return null;
        }
        foundMovie.setTitle(movie.getTitle());
        foundMovie.setDescription(movie.getDescription());
        foundMovie.setGenre(movie.getGenre());
        foundMovie.setRating(movie.getRating());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        return movieDAO.save(foundMovie);

    }

    public void deleteMovie(UUID id) {
        movieDAO.delete(id);
    }

    public Page<Movie> getAllMoviesByFilter(String title, String description, List<GenreEnum> genreList, Float minRating, Float maxRating, String[] sortList, int page, int size) {
        List<Movie> movieList = movieDAO.getAllMoviesByFilter(title, description, genreList, minRating, maxRating, sortList, page, size);
        Sort sortBy = Sort.by(sortList);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        long moviesCount = movieDAO.getMoviesCount(title, description, genreList, minRating, maxRating);

        return new PageImpl<>(movieList, pageable, moviesCount);
    }

}

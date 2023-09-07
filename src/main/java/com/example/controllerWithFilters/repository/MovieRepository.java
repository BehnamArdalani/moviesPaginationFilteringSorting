package com.example.controllerWithFilters.repository;

import com.example.controllerWithFilters.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}

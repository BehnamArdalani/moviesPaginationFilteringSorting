package com.example.controllerWithFilters.repository;

import com.example.controllerWithFilters.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}

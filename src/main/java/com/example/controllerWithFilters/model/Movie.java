package com.example.controllerWithFilters.model;

import com.example.controllerWithFilters.model.enums.GenreEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private GenreEnum genre;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

}

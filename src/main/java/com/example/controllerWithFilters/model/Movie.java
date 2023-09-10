package com.example.controllerWithFilters.model;

import com.example.controllerWithFilters.model.enums.GenreEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "movie")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    @Builder.Default
    private Date releaseDate = new Date();

}
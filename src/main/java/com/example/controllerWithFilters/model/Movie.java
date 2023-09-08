package com.example.controllerWithFilters.model;

import com.example.controllerWithFilters.model.enums.GenreEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@Table(name = "movie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "genre")
    private GenreEnum genre;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "release_date")
    @NonNull
    @Builder.Default
    private Instant releaseDate = Instant.now();

}

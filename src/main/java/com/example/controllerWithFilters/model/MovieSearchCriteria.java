package com.example.controllerWithFilters.model;

import com.example.controllerWithFilters.model.enums.GenreEnum;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchCriteria {
    private String title;
    private String description;
    private List<GenreEnum> genreList;
    private Float minRating;
    private Float maxRating;
}

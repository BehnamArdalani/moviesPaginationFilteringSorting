package com.example.controllerWithFilters.repository;

import com.example.controllerWithFilters.model.Movie;
import com.example.controllerWithFilters.model.MovieSearchCriteria;
import com.example.controllerWithFilters.model.enums.GenreEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MovieCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public MovieCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Movie> getAllMoviesByFilter(String title, String description, List<GenreEnum> genreList, Float minRating, Float maxRating, String[] sortList, int page, int size) {
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
        MovieSearchCriteria movieSearchCriteria = MovieSearchCriteria.builder()
                .title(title)
                .description(description)
                .genreList(genreList)
                .minRating(minRating)
                .maxRating(maxRating)
                .build();
        Predicate predicate = getPredicate(movieSearchCriteria, movieRoot);
        criteriaQuery.where(predicate);

        setOrder(sortList, criteriaQuery, movieRoot);

        TypedQuery<Movie> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);

        return typedQuery.getResultList();
    }

    private void setOrder(String[] sortList, CriteriaQuery<Movie> criteriaQuery, Root<Movie> movieRoot) {
        List<Order> orderList = new ArrayList<>();

        // TODO: Add exception handling...

        if (!sortList[0].contains(",")) {
            if (sortList[1].equalsIgnoreCase("DESC")) {
                orderList.add(criteriaBuilder.desc(movieRoot.get(sortList[0])));
            } else if (sortList[1].equalsIgnoreCase("ASC")) {
                orderList.add(criteriaBuilder.asc(movieRoot.get(sortList[0])));
            }

            criteriaQuery.orderBy(orderList);
            return;
        }

        for (String sort : sortList) {
            String[] sortItem = sort.split(",");
            if (sortItem[1].trim().equalsIgnoreCase("DESC")) {
                orderList.add(criteriaBuilder.desc(movieRoot.get(sortItem[0].trim())));
            } else if (sortItem[1].trim().equalsIgnoreCase("ASC")) {
                orderList.add(criteriaBuilder.asc(movieRoot.get(sortItem[0].trim())));
            }
        }
        criteriaQuery.orderBy(orderList);
    }

    private Predicate getPredicate(MovieSearchCriteria movieSearchCriteria, Root<Movie> movieRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (isNonNull(movieSearchCriteria.getTitle())) {
            predicates.add(criteriaBuilder.like(movieRoot.get("title"),
                    movieSearchCriteria.getTitle()));
        }

        if (isNonNull(movieSearchCriteria.getDescription())) {
            predicates.add(criteriaBuilder.like(movieRoot.get("description"),
                    movieSearchCriteria.getDescription()));
        }

        if (isNonNull(movieSearchCriteria.getGenreList())) {
            CriteriaBuilder.In<GenreEnum> inClause = criteriaBuilder.in(movieRoot.get("genre"));
            movieSearchCriteria.getGenreList().forEach(inClause::value);
            predicates.add(inClause);
        }

        if (isNonNull(movieSearchCriteria.getMinRating()) && isNonNull(movieSearchCriteria.getMaxRating())) {
            predicates.add(criteriaBuilder.between(movieRoot.get("rating"),
                    movieSearchCriteria.getMinRating(), movieSearchCriteria.getMaxRating()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public long getMoviesCount(String title, String description, List<GenreEnum> genreList, Float minRating, Float maxRating) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Movie> countRoot = countQuery.from(Movie.class);
        MovieSearchCriteria movieSearchCriteria = MovieSearchCriteria.builder()
                .title(title)
                .description(description)
                .genreList(genreList)
                .minRating(minRating)
                .maxRating(maxRating)
                .build();
        countQuery.select(criteriaBuilder.count(countRoot)).where(getPredicate(movieSearchCriteria, countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private <T> boolean isNonNull(T object) {
        return Objects.nonNull(object);
    }
}

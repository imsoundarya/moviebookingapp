package com.cognizant.moviebookingapp.repository;

import com.cognizant.moviebookingapp.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findByMovieId(String movieId);

    boolean existsByMovieId(String movieId);

    boolean existsByMovieName(String movieName);

    Optional<Movie> findByMovieName(String movieName);

}

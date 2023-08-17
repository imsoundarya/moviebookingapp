package com.cognizant.moviebookingapp.service;

import com.cognizant.moviebookingapp.model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<?> addMovie(Movie movie);

    ResponseEntity<List<Movie>> getAllMovies();

    ResponseEntity<?> searchMovieById(String movieId);

    ResponseEntity<?> deleteMovie(String movieId);

    ResponseEntity<?> updateMovie(String movieName, int sumTickets);

    ResponseEntity<?> getBookedTicketList(String movieName);

}

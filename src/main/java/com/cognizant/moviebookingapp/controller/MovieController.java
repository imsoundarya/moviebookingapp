package com.cognizant.moviebookingapp.controller;

import com.cognizant.moviebookingapp.exception.AuthorizationException;
import com.cognizant.moviebookingapp.model.Movie;
import com.cognizant.moviebookingapp.model.Ticket;
import com.cognizant.moviebookingapp.service.MovieService;
import com.cognizant.moviebookingapp.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    TicketService ticketService;

    @Autowired
    AuthService authService;


    /// role:admin
    @PostMapping("/addmovie")
    @Operation(summary = "add movie into db(admin)")
    public ResponseEntity<?> addMovies(@RequestBody Movie movie,
                                       @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")) {
            ResponseEntity<?> responseEntity = movieService.addMovie(movie);
            return responseEntity;
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

    @GetMapping("/getAllMovies")
    @Operation(summary = "get all available movies(admin+customer)")
    public ResponseEntity<List<Movie>> getAllMovies(
            @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")
                || authService.validateToken(token).containsValue("customer")) {
            return movieService.getAllMovies();
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

    @GetMapping("/movies/search/{movieId}")
    @Operation(summary = "searching movie by it(admin+customer)")
    public ResponseEntity<?> searchMovieById(@PathVariable(value = "movieId") String movieId,
                                             @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")
                || authService.validateToken(token).containsValue("customer")) {
            return movieService.searchMovieById(movieId);
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

    @PutMapping("/update/{movieName}/{sumTickets}")
    @Operation(summary = "for update of tickets into a movie(admin)")
    public ResponseEntity<?> updateTicketStatus(@PathVariable(value = "movieName") String movieName,
                                                @PathVariable(value = "sumTickets") int sumTickets,
                                                @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")) {
            return movieService.updateMovie(movieName, sumTickets);
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

    @DeleteMapping("/delete/{movieId}")
    @Operation(summary = "for deletion of movie(admin)")
    public ResponseEntity<?> deleteMovieById(@PathVariable String movieId,
                                             @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")) {
            // Allow access
            movieService.deleteMovie(movieId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully deleted movie");
            return ResponseEntity.ok(response);
        } else {
            // Deny access
            throw new AuthorizationException("Access Denied");
        }

    }

    @GetMapping("/getAllTickets")
    @Operation(summary = "for listing all the tickets booked by users(admin)")
    public ResponseEntity<?> getAllTickets(@Parameter(hidden = true) @RequestHeader("Authorization") String token)
            throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")) {
            return ticketService.getAllTickets();
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

    @PostMapping("/book/{movieName}")
    @Operation(summary = "for book a ticket for a movie(admin+customer)")
    public ResponseEntity<?> bookMovie(@PathVariable(value = "movieName") String movieName, @RequestBody Ticket ticket,
                                       @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        Map<String, String> userInfo = authService.validateToken(token);
        if (userInfo.containsValue("admin") || userInfo.containsValue("customer")) {

            String userId = userInfo.keySet().iterator().next();
            System.out.println("useridddd" + userId);
            ticket.setUserId(userId);
            ticketService.bookMovie(movieName, ticket);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully booked movie " + movieName);
            return ResponseEntity.ok(response);
        } else {

            throw new AuthorizationException("Access Denied");
        }

    }

    @GetMapping("/getUserTickets/{userId}") // no need of userId here--fix--if user is already logged in
    @Operation(summary = "for customer to see the booked tickets for a movie(admin+customer)")
    public ResponseEntity<?> getTicketsByUserId(@PathVariable(value = "userId") String userId,
                                                @Parameter(hidden = false) @RequestHeader("Authorization") String token) throws AuthorizationException {
        // Map<String, String> userInfo = authService.validateToken(token);
        if (authService.validateToken(token).containsValue("admin")
                || authService.validateToken(token).containsValue("customer")) {
            return ticketService.getTicketsUser(userId);
        } else {
            // Deny access
            throw new AuthorizationException("Access Denied");
        }

    }


    @GetMapping("/get/bookedSeats/{movieName}")
    @Operation(summary = "for getting the booked seats for a movie(admin+customer)")
    public ResponseEntity<?> getBookedTicketList(@PathVariable(value = "movieName") String movieName,
                                                 @RequestHeader("Authorization") String token) throws AuthorizationException {
        if (authService.validateToken(token).containsValue("admin")
                || authService.validateToken(token).containsValue("customer")) {
            return movieService.getBookedTicketList(movieName);
        } else {
            throw new AuthorizationException("Access Denied");
        }

    }

}

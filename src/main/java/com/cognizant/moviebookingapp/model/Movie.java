package com.cognizant.moviebookingapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "movies")
public class Movie {

    @Id
    @Schema(hidden = true)
    private String movieId;

    @NotBlank
    private String movieName;

    @NotBlank
    private String theaterName;

    @NotNull
    private int totalTickets;

    @NotBlank
    @Schema(hidden = true)
    private String ticketStatus;

    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Movie() {
    }

    public Movie(String movieId, @NotBlank String movieName, @NotBlank String theaterName, @NotNull int totalTickets,
                 @NotBlank String ticketStatus, List<Ticket> tickets) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.totalTickets = totalTickets;
        this.ticketStatus = ticketStatus;
        this.tickets = tickets;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}

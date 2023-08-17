package com.cognizant.moviebookingapp.model;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Ticket {

    private String transactionId;

    @NotNull
    private int numberOfTickets;

    @NotBlank
    @Schema(hidden = true)
    private String movieName;

    @NotBlank
    @Schema(hidden = true)
    private String theaterName;

    @NotBlank
    @Schema(hidden = true)
    private String userId;

    public Ticket() {
    }

    public Ticket(String transactionId, @NotNull @Min(1) int numberOfTickets,
                  @NotBlank String movieName, @NotBlank String theaterName, @NotBlank String userId) {
        this.transactionId = transactionId;
        this.numberOfTickets = numberOfTickets;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

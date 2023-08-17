package com.cognizant.moviebookingapp.service;

import com.cognizant.moviebookingapp.model.Ticket;
import org.springframework.http.ResponseEntity;

public interface TicketService {

    ResponseEntity<?> bookMovie(String movieName, Ticket ticket);

    ResponseEntity<?> getAllTickets();

    ResponseEntity<?> getTicketsUser(String userId);//todo--after auth

}

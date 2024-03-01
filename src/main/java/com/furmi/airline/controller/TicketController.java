package com.furmi.airline.controller;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/ticket/{airlines}")
    public List<Ticket> getByAirlines (@PathVariable String airlines){
        log.info("Getting all tickets sold by {}", airlines);
        return ticketService.getByAirlines(airlines);
    }
    @GetMapping("/ticket/{id}")
    public Ticket getByTicketId (@PathVariable long id){
        log.info("Getting by {}", id);
        return ticketService.getById(id);
    }

    @GetMapping("/ticket")
    public List<Ticket> getAll (){
        log.info("Getting all tickets");
        return ticketService.getAll();
    }

    @GetMapping("/ticket/{landAirport}")
    public List<Ticket> getByLandAirport (@PathVariable String landAirport){
        log.info("Getting by landing Airport {}", landAirport);
        return ticketService.getByLandAirport(landAirport);
    }
}

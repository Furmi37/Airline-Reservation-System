package com.furmi.airline.controller;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/ticket/airlines/{airlines}")
    public List<Ticket> getByAirlines (@PathVariable String airlines){
        log.info("Getting all tickets sold by {}", airlines);
        return ticketService.getByAirlines(airlines);
    }

    @GetMapping("/ticket/id/{id}")
    public Ticket getByTicketId (@PathVariable long id){
        log.info("Getting by id {}", id);
        return ticketService.getById(id);
    }

    @GetMapping("/ticket")
    public List<Ticket> getAll (){
        log.info("Getting all tickets");
        return ticketService.getAll();
    }

    @GetMapping("/ticket/landAirport/{landAirport}")
    public List<Ticket> getByLandAirport (@PathVariable String landAirport){
        log.info("Getting by landing Airport {}", landAirport);
        return ticketService.getByLandAirport(landAirport);
    }

    @PostMapping("/ticket")
    public Long createTicket (@RequestBody Ticket ticket){
        log.info("Creating ticket to {}", ticket.getLandAirport());
        return ticketService.createTicket(ticket);
    }
    @PutMapping("/ticket/time")
    public Long updateTicketStartandLandTime (@RequestParam long id, String startTime, String landTime){
        log.info("Setting new start time {}", startTime);
        Ticket ticket = ticketService.getById(id);
        ticket.setStartTime(startTime);
        ticket.setLandTime(landTime);
        return ticketService.createTicket(ticket);
    }

}

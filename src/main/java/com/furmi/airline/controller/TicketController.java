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
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/id/{id}")
    public Ticket getByTicketId(@PathVariable long id) {
        log.info("Getting by id {}", id);
        return ticketService.getById(id);
    }

    @GetMapping("/airlines/{airlines}")
    public List<Ticket> getByAirlines(@PathVariable String airlines) {
        log.info("Getting all tickets sold by {}", airlines);
        return ticketService.getByAirlines(airlines);
    }

    @GetMapping
    public List<Ticket> getAll() {
        log.info("Getting all tickets");
        return ticketService.getAll();
    }

    @GetMapping("/landAirport/{landAirport}")
    public List<Ticket> getByLandAirport(@PathVariable String landAirport) {
        log.info("Getting tickets by landing Airport {}", landAirport);
        return ticketService.getByLandAirport(landAirport);
    }

    @GetMapping("/startDate/{startDate}")
    public List<Ticket> getByStartDate(@PathVariable String startDate) {
        log.info("Getting tickets by starting date");
        return ticketService.getByStartDate(startDate);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        log.info("Creating ticket to {}", ticket.getLandAirport());
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/time")
    public Ticket updateTicketStartAndLandTime(@RequestParam long ticketId, String startTime, String landTime) {
        log.info("Setting in ticket new start time {}", startTime);
        Ticket ticket = ticketService.getById(ticketId);
        ticket.setStartTime(startTime);
        ticket.setLandTime(landTime);
        return ticketService.createTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable long id) {
        Ticket ticket = ticketService.getById(id);
        ticketService.deleteTicket(ticket);
    }
}

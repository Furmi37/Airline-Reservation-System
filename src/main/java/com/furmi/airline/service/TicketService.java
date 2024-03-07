package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket getById(long id) {
        log.info("Getting ticket by id: " + id);
        return ticketRepository.findByTicketId(id);
    }

    public List<Ticket> getByAirlines(String airlines) {
        return ticketRepository.findByAirlines(airlines);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getByLandAirport(String landAirport) {
        return ticketRepository.findByLandAirport(landAirport);
    }

    public List<Ticket> getByStartDate(String startDate) {
        return ticketRepository.findByStartDate(startDate);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}

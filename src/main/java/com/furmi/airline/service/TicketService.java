package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket getById(long id){
        return ticketRepository.findByTicketId(id);
    }
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getByAirlines (String airlines){
        return ticketRepository.findByAirlines(airlines);
    }

    public Long createTicket (Ticket ticket){
        return ticketRepository.save(ticket).getTicketId();
    }

    public List<Ticket> getByLandAirport(String landAirport){
        return ticketRepository.findAllByLandAirport(landAirport);
    }

    public List<Ticket> getByStartDate (String startDate){
        return ticketRepository.findByStartDate(startDate);
    }
}

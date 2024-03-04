package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void shouldGetTicketById() {
        //given
        long id = 1L;
        Ticket ticket = new Ticket(id, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        when(ticketRepository.findByTicketId(id)).thenReturn(ticket);
        //when
        Ticket ticketResult = ticketService.getById(id);
        //then
        verify(ticketRepository, times(1)).findByTicketId(id);
        assertEquals(ticket, ticketResult);
    }

    @Test
    void shouldGetTicketByAirlines() {
        //given
        String airlines = "Wizz air";
        Ticket ticket = new Ticket(1L, airlines, "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        List<Ticket> tickets = List.of(ticket);
        when(ticketRepository.findByAirlines(airlines)).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getByAirlines(airlines);
        //then
        verify(ticketRepository, times(1)).findByAirlines(airlines);
        assertEquals(tickets, result);
    }

    @Test
    void shouldGetThreeTickets() {
        //given
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        Ticket t2 = new Ticket(2L, "Buzz", "Berlin", "Cracow", "2024-02-29", "16:00", "2024-02-29", "16:49");
        Ticket t3 = new Ticket(3L, "Ryanair", "Rome", "Oslo", "2024-02-29", "13:00", "2024-02-29", "16:49");
        List<Ticket> tickets = List.of(t1, t2, t3);
        when(ticketRepository.findAll()).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getAll();
        //then
        verify(ticketRepository, times(1)).findAll();
        assertEquals(tickets, result);
    }

    @Test
    void ShouldGetTwoTicketByLandAirport() {
        //given
        String landAirport = "Cracow";
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", landAirport, "2024-02-29", "14:00", "2024-02-29", "15:49");
        Ticket t2 = new Ticket(2L, "Buzz", "Berlin", landAirport, "2024-02-29", "16:00", "2024-02-29", "16:49");
        List<Ticket> tickets = List.of(t1, t2);
        when(ticketRepository.findByLandAirport(landAirport)).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getByLandAirport(landAirport);
        //then
        verify(ticketRepository, times(1)).findByLandAirport(landAirport);
        assertEquals(tickets, result);
    }

    @Test
    void shouldGetTwoTicketsByStartDate() {
        //given
        String startDate = "2024-02-29";
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", startDate, "14:00", "2024-02-29", "15:49");
        Ticket t2 = new Ticket(2L, "Buzz", "Berlin", "Oslo", "2024-02-29", "16:00", "2024-02-29", "16:49");
        List<Ticket> tickets = List.of(t1, t2);
        when(ticketRepository.findByStartDate(startDate)).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getByStartDate(startDate);
        //then
        verify(ticketRepository, times(1)).findByStartDate(startDate);
        assertEquals(tickets, result);
    }

    @Test
    void shouldCreateTicket() {
        //given
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        when(ticketRepository.save(t1)).thenReturn(t1);
        //when
        Long result = ticketService.createTicket(t1);
        //then
        verify(ticketRepository, times(1)).save(t1);
        assertEquals(1, result);
    }

    @Test
    void shouldDeleteTicket() {
        //given
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        //when
        ticketService.deleteTicket(t1);
        //then
        verify(ticketRepository, times(1)).delete(t1);
    }
}
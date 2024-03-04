package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.repository.TicketRepository;
<<<<<<< HEAD
=======
import org.junit.jupiter.api.BeforeEach;
>>>>>>> origin/main
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
<<<<<<< HEAD
import org.mockito.stubbing.Answer;
=======
>>>>>>> origin/main

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
<<<<<<< HEAD
        Ticket ticket = new Ticket(id,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
=======
        Ticket ticket = new Ticket(id, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
>>>>>>> origin/main
        when(ticketRepository.findByTicketId(id)).thenReturn(ticket);
        //when
        Ticket ticketResult = ticketService.getById(id);
        //then
<<<<<<< HEAD
        verify(ticketRepository,times(1)).findByTicketId(id);
        assertEquals(ticket,ticketResult);
=======
        verify(ticketRepository, times(1)).findByTicketId(id);
        assertEquals(ticket, ticketResult);
>>>>>>> origin/main
    }

    @Test
    void shouldGetTicketByAirlines() {
        //given
        String airlines = "Wizz air";
<<<<<<< HEAD
        Ticket ticket = new Ticket(1L,airlines,"Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
=======
        Ticket ticket = new Ticket(1L, airlines, "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
>>>>>>> origin/main
        List<Ticket> tickets = List.of(ticket);
        when(ticketRepository.findByAirlines(airlines)).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getByAirlines(airlines);
        //then
<<<<<<< HEAD
        verify(ticketRepository,times(1)).findByAirlines(airlines);
        assertEquals(tickets,result);
=======
        verify(ticketRepository, times(1)).findByAirlines(airlines);
        assertEquals(tickets, result);
>>>>>>> origin/main
    }

    @Test
    void shouldGetThreeTickets() {
        //given
<<<<<<< HEAD
        Ticket t1 = new Ticket(1L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        Ticket t2 = new Ticket(2L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        Ticket t3 = new Ticket(3L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        List<Ticket> tickets = List.of(t1,t2,t3);
=======
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        Ticket t2 = new Ticket(2L, "Buzz", "Berlin", "Cracow", "2024-02-29", "16:00", "2024-02-29", "16:49");
        Ticket t3 = new Ticket(3L, "Ryanair", "Rome", "Oslo", "2024-02-29", "13:00", "2024-02-29", "16:49");
        List<Ticket> tickets = List.of(t1, t2, t3);
>>>>>>> origin/main
        when(ticketRepository.findAll()).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getAll();
        //then
<<<<<<< HEAD
        verify(ticketRepository,times(1)).findAll();
        assertEquals(tickets,result);
    }

    @Test
    void ShouldGetTicketByLandAirport() {
        //given

        //when

        //then
    }

    @Test
    void shouldGetByTicketStartDate() {
        //given

        //when

        //then
=======
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
>>>>>>> origin/main
    }

    @Test
    void shouldCreateTicket() {
        //given
<<<<<<< HEAD

        //when

        //then
=======
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        when(ticketRepository.save(t1)).thenReturn(t1);
        //when
        Long result = ticketService.createTicket(t1);
        //then
        verify(ticketRepository, times(1)).save(t1);
        assertEquals(1, result);
>>>>>>> origin/main
    }

    @Test
    void shouldDeleteTicket() {
        //given
<<<<<<< HEAD

        //when

        //then
=======
        Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
        //when
        ticketService.deleteTicket(t1);
        //then
        verify(ticketRepository, times(1)).delete(t1);
>>>>>>> origin/main
    }
}
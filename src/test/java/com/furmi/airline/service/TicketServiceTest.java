package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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
        Ticket ticket = new Ticket(id,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        when(ticketRepository.findByTicketId(id)).thenReturn(ticket);
        //when
        Ticket ticketResult = ticketService.getById(id);
        //then
        verify(ticketRepository,times(1)).findByTicketId(id);
        assertEquals(ticket,ticketResult);
    }

    @Test
    void shouldGetTicketByAirlines() {
        //given
        String airlines = "Wizz air";
        Ticket ticket = new Ticket(1L,airlines,"Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        List<Ticket> tickets = List.of(ticket);
        when(ticketRepository.findByAirlines(airlines)).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getByAirlines(airlines);
        //then
        verify(ticketRepository,times(1)).findByAirlines(airlines);
        assertEquals(tickets,result);
    }

    @Test
    void shouldGetThreeTickets() {
        //given
        Ticket t1 = new Ticket(1L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        Ticket t2 = new Ticket(2L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        Ticket t3 = new Ticket(3L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        List<Ticket> tickets = List.of(t1,t2,t3);
        when(ticketRepository.findAll()).thenReturn(tickets);
        //when
        List<Ticket> result = ticketService.getAll();
        //then
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
    }

    @Test
    void shouldCreateTicket() {
        //given

        //when

        //then
    }

    @Test
    void shouldDeleteTicket() {
        //given

        //when

        //then
    }
}
package com.furmi.airline.controller;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {
    @Mock
    private TicketService ticketService;
    @InjectMocks
    private TicketController ticketController;
    private MockMvc mockMvc;
    Ticket t1 = new Ticket(null, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");
    Ticket t2 = new Ticket(2L, "Wizz air", "Berlin", "Warsaw", "2024-02-29", "16:00", "2024-02-29", "16:49");

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void shouldReturnTicketWhenGetByTicketId() throws Exception {
        when(ticketService.getById(1L)).thenReturn(t1);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.airlines").value("Wizz air"))
                .andExpect(jsonPath("$.startAirport").value("Rome"))
                .andExpect(jsonPath("$.landAirport").value("Cracow"))
                .andExpect(jsonPath("$.startDate").value("2024-02-29"))
                .andExpect(jsonPath("$.startTime").value("14:00"))
                .andExpect(jsonPath("$.landDate").value("2024-02-29"))
                .andExpect(jsonPath("$.landTime").value("15:49"))
                .andReturn();
    }

    @Test
    void shouldReturnTwoTicketsWhenGetByAirlines() throws Exception {
        List<Ticket> tickets = List.of(t1, t2);

        when(ticketService.getByAirlines("Wizz air")).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/airlines/Wizz air"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[0].startAirport").value("Rome"))
                .andExpect(jsonPath("$[0].landAirport").value("Cracow"))
                .andExpect(jsonPath("$[0].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].startTime").value("14:00"))
                .andExpect(jsonPath("$[0].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].landTime").value("15:49"))

                .andExpect(jsonPath("$[1].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[1].startAirport").value("Berlin"))
                .andExpect(jsonPath("$[1].landAirport").value("Warsaw"))
                .andExpect(jsonPath("$[1].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].startTime").value("16:00"))
                .andExpect(jsonPath("$[1].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].landTime").value("16:49"))
                .andReturn();
    }

    @Test
    void shouldReturnTwoTicketsWhenGetAll() throws Exception {
        List<Ticket> tickets = List.of(t1, t2);

        when(ticketService.getAll()).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[1].startAirport").value("Berlin"))
                .andExpect(jsonPath("$[1].landAirport").value("Warsaw"))
                .andExpect(jsonPath("$[1].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].startTime").value("16:00"))
                .andExpect(jsonPath("$[1].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].landTime").value("16:49"))

                .andExpect(jsonPath("$[0].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[0].startAirport").value("Rome"))
                .andExpect(jsonPath("$[0].landAirport").value("Cracow"))
                .andExpect(jsonPath("$[0].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].startTime").value("14:00"))
                .andExpect(jsonPath("$[0].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].landTime").value("15:49"))
                .andReturn();
    }

    @Test
    void shouldReturnTicketWhenGetByLandAirport() throws Exception {
        List<Ticket> tickets = List.of(t1);
        when(ticketService.getByLandAirport("Cracow")).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/landAirport/Cracow"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[0].startAirport").value("Rome"))
                .andExpect(jsonPath("$[0].landAirport").value("Cracow"))
                .andExpect(jsonPath("$[0].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].startTime").value("14:00"))
                .andExpect(jsonPath("$[0].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].landTime").value("15:49"))
                .andReturn();
    }

    @Test
    void shouldReturnTicketWhenGetByStartDate() throws Exception {
        List<Ticket> tickets = List.of(t1);
        when(ticketService.getByStartDate("2024-02-29")).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/startDate/2024-02-29"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[0].startAirport").value("Rome"))
                .andExpect(jsonPath("$[0].landAirport").value("Cracow"))
                .andExpect(jsonPath("$[0].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].startTime").value("14:00"))
                .andExpect(jsonPath("$[0].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].landTime").value("15:49"))
                .andReturn();
    }

    @Test
    void shouldCallCreateTicketOneTimeWhenCreateTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"airlines\":\"Wizz air\",\"startAirport\":\"Rome\",\"landAirport\":\"Cracow\",\"startDate\":\"2024-02-29\",\"startTime\":\"14:00\",\"landDate\":\"2024-02-29\",\"landTime\": \"15:49\"}"))
                .andExpect(status().isOk());

        verify(ticketService, times(1)).createTicket(t1);
    }

    @Test
    void shouldCAllCreateTicketWhenUpdateTicketStartAndLandTime() throws Exception {
        String startTime = "15:00";
        String landTime = "16:49";
        t1.setTicketId(1L);
        when(ticketService.getById(1L)).thenReturn(t1);
        t1.setStartTime(startTime);
        t1.setLandTime(landTime);
        when(ticketService.createTicket(t1)).thenReturn(t1);

        mockMvc.perform(MockMvcRequestBuilders.put("/ticket/time")
                        .param("ticketId", "1")
                        .param("startTime", startTime)
                        .param("landTime", landTime)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"airlines\":\"Wizz air\",\"startAirport\":\"Rome\",\"landAirport\":\"Cracow\",\"startDate\":\"2024-02-29\",\"startTime\":\"15:00\",\"landDate\":\"2024-02-29\",\"landTime\": \"16:49\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startTime").value(startTime))
                .andExpect(jsonPath("$.landTime").value(landTime))
                .andDo(print());

        verify(ticketService, times(1)).createTicket(t1);
    }

    @Test
    void shouldCallDeleteTicketWhenDeleteTicket() throws Exception {
        when(ticketService.getById(1L)).thenReturn(t1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/ticket/delete/1"));

        verify(ticketService, times(1)).deleteTicket(t1);
    }
}
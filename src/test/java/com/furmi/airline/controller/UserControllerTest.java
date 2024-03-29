package com.furmi.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furmi.airline.model.Ticket;
import com.furmi.airline.model.User;
import com.furmi.airline.service.TicketService;
import com.furmi.airline.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private TicketService ticketService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    User user = new User(null, "Monthy", "Python", "monthy@gmail.com", 26, "male", new HashSet<>());
    Ticket t1 = new Ticket(1L, "Wizz air", "Rome", "Cracow", "2024-02-29", "14:00", "2024-02-29", "15:49");


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void shouldReturnUserWhenGetUserById() throws Exception {
        user.setId(1L);
        when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Monthy"))
                .andExpect(jsonPath("$.lastName").value("Python"))
                .andExpect(jsonPath("$.age").value(26))
                .andExpect(jsonPath("$.gender").value("male"))
                .andReturn();
    }

    @Test
    void shouldReturnUserFromServiceWhenGetByEmail() throws Exception {
        when(userService.getUserByEmail("monthy@gmail.com")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/monthy@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Monthy"))
                .andExpect(jsonPath("$.lastName").value("Python"))
                .andExpect(jsonPath("$.age").value(26))
                .andExpect(jsonPath("$.gender").value("male"))
                .andReturn();
    }

    @Test
    void shouldReturnTicketsWhenGetByUserEmail() throws Exception {
        Ticket t2 = new Ticket(2L, "Buzz", "Berlin", "Warsaw", "2024-02-29", "16:00", "2024-02-29", "16:49");

        when(userService.getUserByEmail("monthy@gmail.com")).thenReturn(user);
        user.getTickets().add(t1);
        user.getTickets().add(t2);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/ticket/monthy@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].airlines").value("Buzz"))
                .andExpect(jsonPath("$[0].startAirport").value("Berlin"))
                .andExpect(jsonPath("$[0].landAirport").value("Warsaw"))
                .andExpect(jsonPath("$[0].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].startTime").value("16:00"))
                .andExpect(jsonPath("$[0].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[0].landTime").value("16:49"))

                .andExpect(jsonPath("$[1].airlines").value("Wizz air"))
                .andExpect(jsonPath("$[1].startAirport").value("Rome"))
                .andExpect(jsonPath("$[1].landAirport").value("Cracow"))
                .andExpect(jsonPath("$[1].startDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].startTime").value("14:00"))
                .andExpect(jsonPath("$[1].landDate").value("2024-02-29"))
                .andExpect(jsonPath("$[1].landTime").value("15:49"))
                .andReturn();
    }

    @Test
    void shouldReturnThreeUsersFromServiceWhenGetAll() throws Exception {
        User user2 = new User(2L, "Steven", "Gerrard", "steven@gmail.com", 42, "male", new HashSet<>());
        User user3 = new User(3L, "Martin", "Schmitt", "martin@gmail.com", 45, "male", new HashSet<>());
        List<User> users = Arrays.asList(user, user2, user3);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Monthy"))
                .andExpect(jsonPath("$[0].lastName").value("Python"))
                .andExpect(jsonPath("$[0].email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$[0].age").value(26))
                .andExpect(jsonPath("$[0].gender").value("male"))

                .andExpect(jsonPath("$[1].firstName").value("Steven"))
                .andExpect(jsonPath("$[1].lastName").value("Gerrard"))
                .andExpect(jsonPath("$[1].email").value("steven@gmail.com"))
                .andExpect(jsonPath("$[1].age").value(42))
                .andExpect(jsonPath("$[1].gender").value("male"))

                .andExpect(jsonPath("$[2].firstName").value("Martin"))
                .andExpect(jsonPath("$[2].lastName").value("Schmitt"))
                .andExpect(jsonPath("$[2].email").value("martin@gmail.com"))
                .andExpect(jsonPath("$[2].age").value(45))
                .andExpect(jsonPath("$[2].gender").value("male"));
    }

    @Test
    void shouldCallCreateUserOneTimeWhenCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Monthy\",\"lastName\":\"Python\",\"email\":\"monthy@gmail.com\",\"age\":\"26\",\"gender\":\"male\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(eq(user));
    }

    @Test
    void shouldCallCreateUserOneTimeWhenUpdateUserFirstName() throws Exception {
        String firstName = "Martin";
        when(userService.getUserByEmail("monthy@gmail.com")).thenReturn(user);
        user.setFirstName(firstName);
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(put("/user/update")
                        .param("firstName", firstName)
                        .param("email", "monthy@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Martin\",\"lastName\":\"Python\",\"email\":\"monthy@gmail.com\",\"age\":\"26\",\"gender\":\"male\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andDo(print());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    void shouldCallCreateUserOneTimeWhenAddTicketToUser() throws Exception {
        when(ticketService.getById(1L)).thenReturn(t1);
        when(userService.getUserByEmail("monthy@gmail.com")).thenReturn(user);
        user.getTickets().add(t1);
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/ticket")
                        .param("email", "monthy@gmail.com")
                        .param("ticketId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Martin\",\"lastName\":\"Python\",\"email\":\"monthy@gmail.com\",\"age\":\"26\",\"gender\":\"male\"," +
                                "\"tickets\": {\"airlines\":\"Wizz air\", \"startAirport\": \"Cracow\",\"startDate\":\"2024-02-29\",\"startTime\":\"14:00\",\"landDate\":\"2024-02-29\",\"landTime\": \"15:49\"}}"))
                .andExpect(jsonPath("$.firstName").value("Monthy"))
                .andExpect(jsonPath("$.lastName").value("Python"))
                .andExpect(jsonPath("$.email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$.age").value(26))
                .andExpect(jsonPath("$.gender").value("male"))
                .andDo(print());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    void shouldCallUserServiceWhenDeleteUser() throws Exception {
        long id = 1L;
        when(userService.getById(id)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{id}", id));

        verify(userService, times(1)).deleteUser(user);
    }
}
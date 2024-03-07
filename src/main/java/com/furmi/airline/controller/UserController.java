package com.furmi.airline.controller;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.model.User;
import com.furmi.airline.service.TicketService;
import com.furmi.airline.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping("/user/id/{id}")
    public User getById(@PathVariable long id) {
        log.info("Getting user by id {}", id);
        return userService.getById(id);
    }

    @GetMapping("/user/{email}")
    public User getByEmail(@PathVariable String email) {
        log.info("Getting user by email {}", email);
        return userService.getUserByEmail(email);
    }

    @GetMapping("/user/ticket/{email}")
    public Set<Ticket> getTicketsByUserEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return user.getTickets();
    }

    @GetMapping("/user")
    public List<User> getAll() {
        log.info("Getting all users");
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        log.info("Creating user");
        return userService.createUser(user);
    }

    @PutMapping("/user/update")
    public User updateUserFirstName(@RequestParam String firstName, String email) {
        log.info("Updating user first name by {}", firstName);
        User user = userService.getUserByEmail(email);
        user.setFirstName(firstName);
        return userService.createUser(user);
    }

    @PutMapping("/user/ticket")
    public User addTicketToUserByEmail(@RequestParam String email, long ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        log.info("Adding ticket " + ticket.getTicketId() + " to user with email: {}", email);
        User user = userService.getUserByEmail(email);
        user.getTickets().add(ticket);
        return userService.createUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        User user = userService.getById(id);
        userService.deleteUser(user);
    }
}

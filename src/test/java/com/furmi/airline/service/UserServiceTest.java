package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.model.User;
import com.furmi.airline.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        //given
        long id = 1L;
        User user = new User(id,"Monthy", "Python", "monthy@gmail.com", 26, "male");
        when(userRepository.findById(id)).thenReturn(user);
        //when
        User userResult = userService.getById(id);
        //then
        verify(userRepository,times(1)).findById(id);
        assertEquals(user,userResult);
    }
    @Test
    void shouldGetUserByEmail() {
        //given
        String email = "monthy@gmail.com";
        Ticket ticket = new Ticket(1L,"Wizz air","Rome", "Cracow", "2024-02-29", "14:00","2024-02-29","15:49");
        User user = new User(1L,"Monthy", "Python", "monthy@gmail.com", 26, "male");
        when(userRepository.findByEmail(email)).thenReturn(user);
        //when
        User userResult = userService.getUserByEmail(email);
        //then
        verify(userRepository, times(1)).findByEmail(email);
        assertEquals(user, userResult);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }
}
package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.model.User;
import com.furmi.airline.repository.UserRepository;
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
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        //given
        long id = 1L;
        User user = new User(id, "Monthy", "Python", "monthy@gmail.com", 26, "male");
        when(userRepository.findById(id)).thenReturn(user);
        //when
        User userResult = userService.getById(id);
        //then
        verify(userRepository, times(1)).findById(id);
        assertEquals(user, userResult);
    }

    @Test
    void shouldGetUserByEmail() {
        //given
        String email = "monthy@gmail.com";
        User user = new User(1L, "Monthy", "Python", "monthy@gmail.com", 26, "male");
        when(userRepository.findByEmail(email)).thenReturn(user);
        //when
        User userResult = userService.getUserByEmail(email);
        //then
        verify(userRepository, times(1)).findByEmail(email);
        assertEquals(user, userResult);
    }

    @Test
    void shouldGetTwoUsersByUserRepositoryFindAll() {
        //given
        User user1 = new User(1L, "Monthy", "Python", "monthy@gmail.com", 26, "male");
        User user2 = new User(2L, "Steven", "Gerrard", "steven@gmail.com", 42, "male");
        List<User> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        //when
        List<User> allUsers = userService.getAllUsers();
        //then
        verify(userRepository, times(1)).findAll();
        assertEquals(users, allUsers);
    }

    @Test
    void shouldCreateUser() {
        //given
        User user1 = new User(1L, "Monthy", "Python", "monthy@gmail.com", 26, "male");
        when(userRepository.save(user1)).thenReturn(user1);
        //when
        Long userResult = userService.createUser(user1);
        //then
        verify(userRepository, times(1)).save(user1);
        assertEquals(1, userResult);
    }

    @Test
    void shouldDeleteUser() {
        //given
        User user1 = new User(1L, "Monthy", "Python", "monthy@gmail.com", 26, "male");
        //when
        userService.deleteUser(user1);
        //then
        verify(userRepository, times(1)).delete(user1);
    }
}
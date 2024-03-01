package com.furmi.airline.service;

import com.furmi.airline.model.Ticket;
import com.furmi.airline.model.User;
import com.furmi.airline.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long createUser(User user){
        return userRepository.save(user).getId();
    }

    public User getById(long id){
        return userRepository.findById(id);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User setTicketToUserByEmail(String email, Ticket ticket){
        User user = userRepository.findByEmail(email);
        user.getTickets().add(ticket);
        return userRepository.save(user);
    }


}

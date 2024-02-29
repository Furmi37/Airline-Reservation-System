package com.furmi.airline.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "tickets")
@EqualsAndHashCode(exclude = "tickets")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name ="user_id", nullable = false)
    private Set<Ticket> tickets = new HashSet<>();

}

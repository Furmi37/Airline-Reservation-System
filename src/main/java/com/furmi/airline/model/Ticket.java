package com.furmi.airline.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    private String airlines;
    private String startAirport;
    private String landAirport;
    private String startDate;
    private String startTime;
    private String landDate;
    private String landTime;
}

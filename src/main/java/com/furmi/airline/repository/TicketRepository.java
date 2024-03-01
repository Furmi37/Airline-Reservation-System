package com.furmi.airline.repository;

import com.furmi.airline.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTicketId(long id);

    List<Ticket> findByAirlines (String airlines);

    List<Ticket> findAllByLandAirport(String landAirport);

    List<Ticket> findByStartDate(String startDate);


}

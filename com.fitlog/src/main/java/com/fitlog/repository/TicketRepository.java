package com.fitlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitlog.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

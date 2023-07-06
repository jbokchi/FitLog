package com.fitlog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import com.fitlog.dto.TicketDto;
import com.fitlog.entity.Ticket;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

public interface TicketRepository extends JpaRepository<Ticket, Long>,
										QuerydslPredicateExecutor<Ticket>,
										TicketRepositoryCustom{
	

}

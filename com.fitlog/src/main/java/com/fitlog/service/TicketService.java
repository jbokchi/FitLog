package com.fitlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.TicketDto;
import com.fitlog.entity.Member;
import com.fitlog.entity.Ticket;
import com.fitlog.repository.MemberRepository;
import com.fitlog.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

	private final MemberRepository memberRepository;
	private final TicketRepository ticketRepository;
	
	public List<TicketDto> getTicketList(String email){
		
		List<TicketDto> ticketDto = new ArrayList<>();
		
		Member member = memberRepository.findByEmail(email);
//		List<Ticket> tickets = ticketRepository.
		
	}
}

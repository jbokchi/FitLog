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

//	private final MemberRepository memberRepository;
//	private final TicketRepository ticketRepository;
//	
//	public List<TicketDto> getTicketList(String email){
//		
//		List<TicketDto> ticketDtoList = new ArrayList<>();
//		
//		//현재 로그인한 회원의 회원권(ticket)을 조회함
//		Member member = memberRepository.findByEmail(email);
//		List<Ticket> tickets = ticketRepository.findByMemberNum(member.getMemberNum());
//		
//		if(tickets.isEmpty()) {
//			return ticketDtoList;
//		}
//		
//		for(Ticket ticket : tickets) {
//			TicketDto ticketDto = TicketDto.builder()
//					.ticketNum(ticket.getTicketNum())
//					.ticketName(ticket.getTicketName())
//					.ticketCount(ticket.getTicketCount())
//					.ticketPeriod(ticket.getTicketPeriod())
//					.memberNum(ticket.getMember().getMemberNum())
//					.build();
//			ticketDtoList.add(ticketDto);
//					
//		}
//		
//		return ticketDtoList;
//		
//	}
}

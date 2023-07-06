package com.fitlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.TicketDto;
import com.fitlog.entity.Member;
import com.fitlog.repository.MemberRepository;
import com.fitlog.repository.TicketRepository;
import com.fitlog.repository.TicketRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

	private final MemberRepository memberRepository;
	private final TicketRepository ticketRepository;
	
	public List<TicketDto> getTicketList(String email){
		
		List<TicketDto> ticketDtoList = new ArrayList<>();
		
		//현재 로그인한 회원의 회원권(ticket)을 조회함
		Member member = memberRepository.findByEmail(email);
		List<TicketDto> ticketsDtoList = ticketRepository.findTicketDtoList(member.getMemberNum());
		
		if(ticketsDtoList.isEmpty()) {
			return ticketDtoList;
		}
					
		return ticketDtoList;
		
	}
}

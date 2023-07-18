package com.fitlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.RegisterDto;
import com.fitlog.dto.TicketDto;
import com.fitlog.entity.Member;
import com.fitlog.repository.CenterRepository;
import com.fitlog.repository.MemberRepository;
import com.fitlog.repository.RegisterRepository;
import com.fitlog.repository.TicketRepository;
import com.fitlog.repository.TicketRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

	private final MemberRepository memberRepository;
	private final TicketRepository ticketRepository;
	private final CenterRepository centerRepository;
	private final RegisterRepository registerRepository;
	
	
	//일반회원의 자신의 회원권 목록 조회
	public List<TicketDto> getTicketListByMember(String email){
				
		List<TicketDto> emptyTicket = new ArrayList<TicketDto>();
		
		//현재 로그인한 회원의 회원권(ticket)을 조회함
		Member member = memberRepository.findByEmail(email);
		List<TicketDto> ticketDtoList = ticketRepository.findMyTicketDtoList(member.getMemberNum());
		
		if(ticketDtoList.isEmpty()) {
			return emptyTicket;
		}
							
		return ticketDtoList;
		
	}
	
	//센터의 티켓관리
	//1.로그인한 강사의 소속 센터 목록 조회
	public List<RegisterDto> getRegisterDtoByMemberNum(String email){
		//강사의 센터 목록 
		 Member member = memberRepository.findByEmail(email); 
		 List<RegisterDto> registerDtoList = registerRepository.findRegisterDtoList(member.getMemberNum());
		 
		 return registerDtoList;
	}
	
	//2.강사의 소속 센터가 가지고 있는 회원권 목록 조회
	 public List<TicketDto> getTicketByCenter(Long centerNum){
	 
		 //빈 회원권
		 List<TicketDto> emptyTicket = new ArrayList<>();
		 //centerNum으로 조회한 회원권
		 List<TicketDto> ticketDtoList = ticketRepository.findCenterTicketDtoList(centerNum);
		 
		 if(ticketDtoList.isEmpty()) {
			 return emptyTicket;
		 }
		 
		 return ticketDtoList;

	 }
	 
}

package com.fitlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.MemberDto;
import com.fitlog.dto.TicketDto;
import com.fitlog.entity.Member;
import com.fitlog.entity.Ticket;
import com.fitlog.repository.TicketRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketServiceTest {

	
	@Autowired
	MemberService memberService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	
	//회원정보를 담은 Member 엔티티를 만드는 메서드 작성
	public Member createNomalMember() {
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail("green@naver.com");
		memberDto.setPassword("1111");
		memberDto.setNicknm("김그린");
		
		//엔티티에 회원정보를 저장하고 반환
		return Member.createNormalMember(memberDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("티켓목록 테스트")
	public void ticketListTest() {
		
		//엔티티 생성
		Member member = createNomalMember();
		//DB 저장
		Member savedMember = memberService.saveMember(member);
		
		TicketDto ticketDto1 = new TicketDto();
		ticketDto1.setMemberNum(savedMember.getMemberNum());
		ticketDto1.setTicketName("PT 10회 이용권");
		ticketDto1.setTicketCount(0);
		ticketDto1.setTicketPeriod(10);
		
		TicketDto ticketDto2 = new TicketDto();
		ticketDto2.setMemberNum(savedMember.getMemberNum());
		ticketDto2.setTicketName("여름맞이 EVENT");
		ticketDto2.setTicketCount(30);
		ticketDto2.setTicketPeriod(90);
		
		//엔티티 생성
		Ticket ticket1 = new Ticket(ticketDto1);
		Ticket ticket2 = new Ticket(ticketDto2);
		
		//DB 저장
		Ticket savedTicket1 = ticketRepository.save(ticket1);
		Ticket savedTicket2 = ticketRepository.save(ticket2);
		
		System.out.println(ticketService.getTicketList(savedMember.getEmail()));
		
		assertEquals(ticketService.getTicketList(savedMember.getEmail()).size(), 1);
		
	}
}

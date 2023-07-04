package com.fitlog.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.MemberDto;
import com.fitlog.dto.TicketDto;
import com.fitlog.repository.MemberRepository;
import com.fitlog.repository.TicketRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class TicketTest {
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EntityManager em;
	
	public Member createMember() {
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail("hello@naver.com");
		memberDto.setNicknm("그린");
		memberDto.setPassword("1234");
		
		return Member.createNormalMember(memberDto, passwordEncoder);
	}
	
	
	public Ticket createTicket1() {
		TicketDto ticketDto = new TicketDto();
		ticketDto.setTicketName("여름맞이 30회 event");
		ticketDto.setTicketCount(30);
		ticketDto.setTicketPeriod(90);
		
		Ticket ticket = new Ticket(ticketDto);
		
		return ticket;
	}
	
	public Ticket createTicket2() {
		TicketDto ticketDto = new TicketDto();
		ticketDto.setTicketName("pt 10회 이용권");
		ticketDto.setTicketCount(10);
		ticketDto.setTicketPeriod(60);
		
		Ticket ticket = new Ticket(ticketDto);
		
		return ticket;
	}
	
	@Test
	@DisplayName("티켓-회원 매핑 테스트")
	public void findTicketAndMemberTest() {
		Member member = createMember();
		memberRepository.save(member);
		
		Ticket ticket1 = createTicket1();
		Ticket ticket2 = createTicket2();
		
		
		ticket1.setMember(member);
		ticket2.setMember(member);
		ticketRepository.save(ticket1);
		ticketRepository.save(ticket2);
		
		em.flush();
		
		em.clear();
		
		
		Ticket savedTicket1 = ticketRepository.findById(ticket1.getTicketNum())
				.orElseThrow(EntityNotFoundException::new);
		Ticket savedTicket2 = ticketRepository.findById(ticket2.getTicketNum())
				.orElseThrow(EntityNotFoundException::new);
		
		
		assertEquals(savedTicket1.getMember().getMemberNum(), member.getMemberNum());
		assertEquals(savedTicket2.getMember().getMemberNum(), member.getMemberNum());
	}
}

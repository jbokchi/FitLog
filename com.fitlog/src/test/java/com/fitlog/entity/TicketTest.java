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
	
	
	@Test
	@DisplayName("티켓-회원 매핑 테스트")
	public void findTicketAndMemberTest() {
		Member member = createMember();
		memberRepository.save(member);
		
		Ticket ticket = new Ticket();
		ticket.setNormalMember(member);
		ticketRepository.save(ticket);
		
		em.flush();
		
		em.clear();
		
		
		Ticket savedTicket = ticketRepository.findById(ticket.getTicketNum())
				.orElseThrow(EntityNotFoundException::new);
		
		assertEquals(savedTicket.getNormalMember().getMemberNum(), member.getMemberNum());
	}
}

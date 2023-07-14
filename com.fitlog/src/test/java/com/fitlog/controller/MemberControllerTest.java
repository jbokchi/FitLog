package com.fitlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.constant.Role;
import com.fitlog.dto.MemberDto;
import com.fitlog.entity.Member;
import com.fitlog.service.MemberService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc //HTTP 요청을 모방하여 컨트롤러의 동작을 테스트하는데 사용함
@TestPropertySource(locations="classpath:application-test.properties")
public class MemberControllerTest {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createNomalMember(String email, String password) {
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(email);
		memberDto.setNicknm("그린");
		memberDto.setPassword(password);
		
		Member member = new Member(memberDto, Role.NORMAL, passwordEncoder);
		
		return memberService.saveMember(member);
	}
	
	public Member createInstructorMember(String email, String password) {
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(email);
		memberDto.setNicknm("강사");
		memberDto.setPassword(password);
		
		Member member = new Member(memberDto, Role.INSTRUCTOR, passwordEncoder);
		
		return memberService.saveMember(member);
	}
	
	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String email = "green@naver.com";
		String password = "1111";
		Member test1 = this.createInstructorMember(email, password);
		
		//formLogin(): 로그인 폼을 기반으로 한 요청을 설정할 수 있도록 함
		mockMvc.perform(formLogin().userParameter("email") //로그인 요청을 모방함
				.loginProcessingUrl("/member/login")//로그인 처리 URL 설정
				.user(email) 		//로그인 요청에 사용될 이메일
				.password(password))//로그인 요청에 사용될 패스워드 설정
				.andExpect(SecurityMockMvcResultMatchers.authenticated());//테스트 결과를 검증함
		
		assertEquals(test1.getRole(), Role.INSTRUCTOR);
	}	
	
	@Test
	@DisplayName("로그인 실패 테스트")
	public void loginFailTest() throws Exception {
		String email = "green@naver.com";
		String password = "1111";
		this.createNomalMember(email, password);
		
		//formLogin(): 로그인 폼을 기반으로 한 요청을 설정할 수 있도록 함
		mockMvc.perform(formLogin().userParameter("email") //로그인 요청을 모방함
				.loginProcessingUrl("/member/login")//로그인 처리 URL 설정
				.user(email)		//로그인 요청에 사용될 이메일
				.password("2222"))	//로그인 요청에 사용될 패스워드 설정
				.andExpect(SecurityMockMvcResultMatchers.unauthenticated());//테스트 결과를 검증함
					
	}
	
	
	@Test
	@DisplayName("강사 로그인 테스트")
	public void loginInstructorTest() {
		
	}
}

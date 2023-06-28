package com.fitlog.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitlog.dto.MemberDto;
import com.fitlog.entity.Member;
import com.fitlog.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/type")
	public String MemberType() {
		return "member/memberType";
	}
	
	@GetMapping("/normal/join")
	public String normalForm(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/join";
	}
	
	@GetMapping("/instructor/join")
	public String insturctorForm(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/join";
	}
	
	
	//@validate : 검증 기능을 활성화
	//데이터 검증 규칙에 따라 입력값이 유효한지 확인한 다음
	//유효하지 않으면 BindingResult 객체에 해당 필드의 에러 정보를 저장
	@PostMapping("/normal/join")
	public String normalJoin(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		
		try {
			Member member = Member.createNormalMember(memberDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}
		return "redirect:/";
		
		
	}
	
	
	@PostMapping("/instructor/join")
	public String InstructorJoin(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		
		try {
			Member member = Member.createInstructorMember(memberDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}
		return "redirect:/";
		
	}

	//로그인 호출
	@GetMapping("/login")
	public String loinMember() {
		return "/member/login";
	}
	
	//로그아웃 호출
	@GetMapping("/logout")
	public String logout() {
		return "member/logout";
	}
}

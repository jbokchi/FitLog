package com.fitlog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitlog.dto.CenterDto;
import com.fitlog.dto.MemberDto;
import com.fitlog.entity.Center;
import com.fitlog.entity.Member;
import com.fitlog.entity.Register;
import com.fitlog.service.CenterService;
import com.fitlog.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final CenterService centerService;
	private final MemberService memberService;
	
	@GetMapping("/type")
	public String MemberType() {
		return "member/memberType";
	}
	
	@GetMapping("/normal/join")
	public String normalForm(Model model) {
		List<CenterDto> centerDtoList = centerService.getCenterList();
		
			
		model.addAttribute("memberDto", new MemberDto());
		model.addAttribute("centerDtoList", centerDtoList);
		model.addAttribute("type", "normal");
		return "member/join";
	}
	
	@GetMapping("/instructor/join")
	public String insturctorForm(Model model) {
		
		List<CenterDto> centerDtoList = centerService.getCenterList();
		
		model.addAttribute("memberDto", new MemberDto());
		model.addAttribute("centerDtoList", centerDtoList);
		model.addAttribute("type", "instructor");
		
		
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
		
		Member savedMember = new Member();
		
		try {
			Member member = memberService.createNormalMember(memberDto);
			System.out.println("member : " + member);
			savedMember = memberService.saveMember(member);
			
		} catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}
		
		if(memberDto.getCenterNum().isEmpty()) {
			return "member/login";
		}
		
		try {
			List<Register> registerList = memberService.createRegister(memberDto, savedMember);
			memberService.saveRegister(registerList);
		}catch(Exception e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}

		return "member/login";

	}
	
	@PostMapping("/instructor/join")
	public String InstructorJoin(@Valid MemberDto memberDto, CenterDto centerDto, String centerName, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/join";
		}
		
		Member savedMember = new Member();
		
		try {
			Member member = memberService.createInstructorMember(memberDto);
			
			System.out.println("member : " + member);
			savedMember = memberService.saveMember(member);
			
		} catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}
		
		if(memberDto.getCenterNum().isEmpty()) {
			return "member/login";
		}
		
		try {
			List<Register> registerList = memberService.createRegister(memberDto, savedMember);
			memberService.saveRegister(registerList);
		}catch(Exception e){
			model.addAttribute("errorMessage", e.getMessage());
			return "member/join";
		}

		return "member/login";

		
	}

	//로그인 호출
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	//로그인 에러
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요");
		return "member/login";
	}
	//로그아웃 호출
	@GetMapping("/logout")
	public String logoutForm() {
		return "member/logout";
	}
	
	@PostMapping("/login")
	public String loginMember() {
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logoutMember() {
		return "redirect:/";
	}
}

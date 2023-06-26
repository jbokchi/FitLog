package com.fitlog.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fitlog.dto.MemberDto;
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
	
	@GetMapping("/join")
	public String MemberJoin(Model model) {
		model.addAttribute("MemberDto", new MemberDto());
		return "member/join";
	}
	
	@PostMapping("/join")
	public String MemberJoin(@RequestParam("type") String type,  Model model) {
		model.addAttribute("type", type);
		model.addAttribute("MemberDto", new MemberDto());
		return "member/join";
	}
	
}

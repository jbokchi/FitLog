package com.fitlog.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitlog.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scheduler")
public class SchedulerContrller {

	@GetMapping("/tickets")
	public String selectTicketList(){
		return "scheduler/selectTicket";
	}
}

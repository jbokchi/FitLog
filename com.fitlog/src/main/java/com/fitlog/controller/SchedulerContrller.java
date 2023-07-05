package com.fitlog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitlog.dto.TicketDto;
import com.fitlog.service.MemberService;
import com.fitlog.service.TicketService;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scheduler")
public class SchedulerContrller {

//	private final TicketService ticketService;
//	
//	@GetMapping("/tickets")
//	public String selectTicketList(Principal principal, Model model){
//		
//		List<TicketDto> ticketDtoList = ticketService.getTicketList(principal.getName());
//		
//		model.addAttribute("ticketDtoList", ticketDtoList);
//		return "scheduler/selectTicket";
//	}
}

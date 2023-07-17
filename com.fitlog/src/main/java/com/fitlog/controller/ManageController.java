package com.fitlog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitlog.dto.RegisterDto;
import com.fitlog.service.TicketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {

	private final TicketService ticketService;
	
	@GetMapping("/tickets")
	public String ticketList(Principal principal, Model model) {
		
		List<RegisterDto> registerDtoList = ticketService.getRegisterDtoByMemberNum(principal.getName());
		
		model.addAttribute("registerDtoList", registerDtoList);
		
		
		System.out.println("registerDtoList" + registerDtoList);
		return "manage/regitTicket";
	}
	

}

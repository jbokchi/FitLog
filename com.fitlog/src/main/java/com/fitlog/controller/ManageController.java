package com.fitlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {

	@GetMapping("/tickets")
	public String ticketList() {
		return "manage/regitTicket";
	}
	
}

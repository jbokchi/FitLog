package com.fitlog.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fitlog.dto.CenterDto;
import com.fitlog.dto.RegisterDto;
import com.fitlog.dto.TicketDto;
import com.fitlog.service.CenterService;
import com.fitlog.service.MemberService;
import com.fitlog.service.TicketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {

	private final TicketService ticketService;
	private final CenterService centerService;
	
	@GetMapping("/tickets")
	public String getCenterList(Principal principal, Model model) {
		
		List<RegisterDto> registerDtoList = ticketService.getRegisterDtoByMemberNum(principal.getName());
		
		List<CenterDto> centerDtoList = new ArrayList<>();
		CenterDto getCenterNum = new CenterDto();
		
		try {
			for(RegisterDto registerDto : registerDtoList) {
				
				CenterDto centerDto = centerService.getCenterByCenterNum(registerDto.getCenterNum());
				centerDtoList.add(centerDto);
				
				model.addAttribute("getCenterNum", getCenterNum);
				model.addAttribute("centerDtoList", centerDtoList);
			}
		} catch(EntityNotFoundException e) {
			model.addAttribute("errorMessage", "소속 센터가 없습니다.");
			return "redirect:/";
		}

		return "manage/regitTicket";
	}
	
	@PostMapping("/tickets/center")
	public String getTicketListByCenter(@Valid CenterDto getCenterNum, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "manage/regitTicket";
		}
		
		System.out.println("센터번호" + getCenterNum);
		try {
			List<TicketDto> ticketDtoList = ticketService.getTicketByCenter(getCenterNum.getCenterNum());
			model.addAttribute("ticketDtoList", ticketDtoList);
		}catch(EntityNotFoundException e) {
			model.addAttribute("errorMessage", "티켓 목록이 없습니다.");
			return "manage/regitTicket";
		}
		
		return "manage/regitTicket";
		
	}

}

package com.fitlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TicketDto {
	
	private Long ticketNum;	
	
	private String ticketName;
	
	private Integer ticketCount;
	
	private Integer ticketPeriod;
}

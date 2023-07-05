package com.fitlog.dto;

import com.fitlog.entity.Ticket;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class TicketDto {
	
	private Long ticketNum;	
	
	private String ticketName;
	
	private Integer ticketCount;
	
	private Integer ticketPeriod;
	
	private Long memberNum;

	@QueryProjection
	public TicketDto(Long ticketNum, String ticketName, Integer ticketCount, Integer ticketPeriod, Long memberNum) {
		this.ticketNum = ticketNum;
		this.ticketName = ticketName;
		this.ticketCount = ticketCount;
		this.ticketPeriod = ticketPeriod;
		this.memberNum = memberNum;
	}
}

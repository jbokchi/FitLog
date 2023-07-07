package com.fitlog.dto;

import java.util.Date;

import com.querydsl.core.annotations.QueryProjection;

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

	private Date startDate;
	
	private Date endDate;

	@QueryProjection
	public TicketDto(Long ticketNum, String ticketName, Integer ticketCount, Integer ticketPeriod, Long memberNum,
			Date startDate, Date endDate) {
		super();
		this.ticketNum = ticketNum;
		this.ticketName = ticketName;
		this.ticketCount = ticketCount;
		this.ticketPeriod = ticketPeriod;
		this.memberNum = memberNum;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
}

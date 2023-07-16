package com.fitlog.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty
	private String ticketName;
	@NotBlank
	private Integer ticketCount;
	@NotBlank
	private Long memberNum;
	@NotBlank	
	private Date startDate;
	@NotBlank
	private Date endDate;
	
	private Long centerNum;
	

	@Builder
	@QueryProjection
	public TicketDto(String ticketName, Integer ticketCount, Long memberNum,
			Date startDate, Date endDate, Long centerNum) {
		this.ticketName = ticketName;
		this.ticketCount = ticketCount;
		this.memberNum = memberNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.centerNum = centerNum;
	}

	
	
	
	
	
}

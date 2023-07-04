package com.fitlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fitlog.dto.TicketDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tk_num")
	private Long ticketNum;
	
//	@Column(name="tk_cnum")
//	private Long centerNum;

	@ManyToOne
	@JoinColumn(name="m_num")
	private Member member;
	
	@Column(name="tk_name")
	private String ticketName;
	@Column(name="tk_count")
	private Integer ticketCount;
	@Column(name="tk_period")
	private Integer ticketPeriod;
	
	public Ticket(TicketDto ticket) {
		this.ticketName = ticket.getTicketName();
		this.ticketCount = ticket.getTicketCount();
		this.ticketPeriod = ticket.getTicketPeriod();
	}

	
}	
	


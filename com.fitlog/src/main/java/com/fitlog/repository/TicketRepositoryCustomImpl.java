package com.fitlog.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.fitlog.dto.TicketDto;
import com.fitlog.entity.QMember;
import com.fitlog.entity.QTicket;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

//Querydsl로 조회할 메서드를 구현
public class TicketRepositoryCustomImpl implements TicketRepositoryCustom{

	//동적 쿼리를 생성하기 위해 JPAQueryFactory 사용
	public JPAQueryFactory queryFactory;

//	@Override
//	public List<TicketDto> findTicketDtoList(Long memberNum) {
//		QTicket ticket = QTicket.ticket;
//		QMember member = QMember.member;
//		
//		return queryFactory
//				.select(new QTicketDto(
//						ticket.ticketNum,
//						ticket.ticketName,
//						ticket.ticketCount,
//						ticket.ticketPeriod,
//						member.memberNum)
//				.from(ticket)
//				.leftJoin(ticket.ticketNum, ticket)
//				.where(
//						isAllCountUse()))
//				
//	}
	
	//BooleanExpression : Querydsl에서 조건을 표현하기 위해 사용하는 인터페이스
	//					: SQL의 where 조건에 해당하는 부분
	
	//횟수를 다 소진핸는지
	private BooleanExpression isAllCountUse(Integer ticketCount) {
		return ticketCount <=0 ? null : QTicket.ticket.ticketCount.eq(ticketCount);
	}
	
	//기간이 넘었는지
	private BooleanExpression isPeriodOver(Integer ticketPeriod) {
		return ticketPeriod <= 0 ? null : QTicket.ticket.ticketPeriod.eq(ticketPeriod);
	}
	
	//JPAQueryFactory의 생성자로 EntityManager를 초기화함
	public TicketRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	
	
	
	
}

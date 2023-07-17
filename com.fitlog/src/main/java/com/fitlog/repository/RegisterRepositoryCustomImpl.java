package com.fitlog.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.fitlog.dto.QRegisterDto;
import com.fitlog.dto.RegisterDto;
import com.fitlog.entity.QCenter;
import com.fitlog.entity.QMember;
import com.fitlog.entity.QRegister;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RegisterRepositoryCustomImpl implements RegisterRepositoryCustom{

	//동적 쿼리를 생성하기 위해 JPAQueryFactory 사용
	public JPAQueryFactory queryFactory;

	//JPAQueryFactory의 생성자로 EntityManager를 초기화함
	public RegisterRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<RegisterDto> findRegisterDtoList(Long memberNum) {
		
		QRegister register = QRegister.register;
		QMember member = QMember.member;
		QCenter center = QCenter.center;
		
		return queryFactory
				.select(
						new QRegisterDto(
								member.memberNum,
								center.centerNum
						)
				.from(register)
				.join(register.center, center)
				.join(register.member, member)
				.where(
						member.memberNum.eq(memberNum)
						)
				.orderBy(register.registerNum.asc())
				.fetch();	

	}
}

package com.fitlog.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

//Querydsl을 사용하기 위해서 JPAQueryFactroy가 필요한데
//Querydsl을 사용하는 Repository에서 주입받으려면 불편하다.
//따라서 Config를 생성하여 JPAQueryFactory를 Bean으로 등록하고
//사용할 Repository에서 생성자를 주입받는다.
@Configuration
public class JpaConfig {
	
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}

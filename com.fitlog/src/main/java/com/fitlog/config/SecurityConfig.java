package com.fitlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //스프링 애플리케이션 컨텍스트에 빈 구성 제공
@EnableWebSecurity //스프링 시큐리티 활성화
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.formLogin()
			//로그인 설정
			.loginPage("/member/login") //로그인 페이지 url 설정
			.defaultSuccessUrl("/")		//로그인 성공시 이동할 url 설정
			.usernameParameter("email") //로그인시 사용할 파라미터 이름
			.failureUrl("/member/login/error") //로그인 실패시 이동할 url 설정
					
			.and()
			
			//로그아웃 설정
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //로그아웃에 대한 url 설정
			.logoutSuccessUrl("/"); //로그아웃 성공시 이동할 url 설정
		
		return http.build();

	}
}

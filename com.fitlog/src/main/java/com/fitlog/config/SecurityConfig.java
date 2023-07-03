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
		
		
		//스프링 시큐리티에 httpServletRequest를 이용하겠다는 의미
		http.authorizeRequests()
		//모든 사용자가 인증없이 해당 경로에 접근할 수 있도록 설정
		.mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
		.mvcMatchers("/", "/member/**", "/today/**", "/board/**").permitAll()
		//normal으로 시작하는 경로는 해당 계정이 일반회원일 때만 접근하도록 설정
		.mvcMatchers("/scheduler/**").hasRole("NORMAL")
		//normal으로 시작하는 경로는 해당 계정이 강사일 때만 접근하도록 설정
		.mvcMatchers("/scheduler/**").hasRole("INSTRUCTOR")
		//나머지는 모두 다 인증을 요구
		.anyRequest().authenticated();
		
		//인증되지 않는 사용자가 리소스에 접근할 때 수행되는 핸들러 목록
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();

	}
}

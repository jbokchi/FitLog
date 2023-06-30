package com.fitlog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

//사용자가 리소스를 요청할 때 정상적인 요청인지 확인하기 위한 페이지
//AuthenticationEntryPoint
//: 인증이 되지 않은 요청이 보호받고 있는 리소스에 접근하고자 할 때 호출되는 지점(인증진입점)
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	//commence : 인증이 필요한 리소스에 접근할 때 호출디며 인증 예외가 발생한 경우 실행됨
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//해당 메서드가 호출되면 HTTP 응답을 생성하고 상태코드를 아래와 같이 설정하여 클라이언트에게 메세지를 전달
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorezed");
		
	}

}

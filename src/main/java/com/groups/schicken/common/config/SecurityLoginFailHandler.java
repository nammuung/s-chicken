package com.groups.schicken.common.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.security.auth.login.AccountException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 
		log.info("exception =  {}", exception);
		
		
		String message= "로그인 실패";
		
		if(exception instanceof BadCredentialsException) {
			message="비밀번호가 틀렸습니다.";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			message="아이디가 존재하지 않습니다.";
		}
		if(exception instanceof AccountExpiredException) {
			message="계정 유효기간이 만료 되었습니다.";
		}
		if(exception instanceof LockedException) {
			message = "계정이 잠겼습니다.";
		}
		if(exception instanceof CredentialsExpiredException) {
			message="비밀번호 유효기간 종료 되었습니다.";
		}
		if(exception instanceof DisabledException) {
			message="계정이 휴면 처리 되었습니다.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("./login?message="+message);
		
	}

	
	
}

package com.sse.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

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
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String message = "로그인 실패";
		
		log.error("Exception:{}",exception);
		
		if(exception instanceof BadCredentialsException) {
//			비밀번호 틀림
			message="비밀번호를 확인하세요";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
//			아이디 틀림
			message="없는 아이디입니다";
		}
		
		if(exception instanceof AccountExpiredException) {
//			membervo의 isAccountNonExpired() false일때 (사용자 계정의 유효 기간이 만료 되었습니다.)
			message="만료된 계정입니다 관리자에게 문의하세요";
		}
		
		if(exception instanceof LockedException) {
//			membervo의 isAccountNonLocked() false일때 (사용자 계정이 잠겨 있습니다.)
			message="잠긴 계정입니다 관리자에게 문의하세요";
		}
		
		if(exception instanceof CredentialsExpiredException) {
//			membervo의 isCredentialsNonExpired() false일때 (자격 증명 유효 기간이 만료되었습니다. 비밀번호 기간 만료)
			message="비밀번호의 유효기간이 종료";
		}
		
		if(exception instanceof DisabledException) {
//			membervo의 isEnabled() false일때 (유효하지 않은 사용자입니다.)
			message="휴면계정입니다 관리자에게 문의하세요";
		}
		
		message = URLEncoder.encode(message,"UTF-8");
		
		response.sendRedirect("/member/login?message="+message);
		
	}
	
}

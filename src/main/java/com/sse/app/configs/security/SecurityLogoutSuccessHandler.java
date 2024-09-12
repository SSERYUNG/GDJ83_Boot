package com.sse.app.configs.security;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sse.app.members.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
//		1. AccessTocken 으로 로그아웃 하기 : 사용자의 AccessToken이 필요!
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		
//		일반로그인인지 소셜 로그인인지 구별하기 위한 조건식
		if(memberVO.getSns()==null) {
			response.sendRedirect("/");
			return;
		}
		
		log.info("===AccessToken=== : {}",memberVO.getAccessToken());
		if(memberVO.getSns().equals("kakao")) {
//		RestTemplate 방식으로 로그아웃 해보자
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers  = new HttpHeaders();
//			headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
//			위에거랑 같은거임
			headers.setBearerAuth(memberVO.getAccessToken());
			
//			헤더 값 보내려고
			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers);
			
//			로그아웃 요청에 대한 응답을 받아야지
			ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
			log.info("로그아웃 id:{}",res.getBody());
			response.sendRedirect("/");
			return;
		}
		
	}
	
}

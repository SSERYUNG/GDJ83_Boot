package com.sse.app.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//DefaultOAuth2UserService 얘는 소셜로그인을 위해 필요한 상속 클래스
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;

//	로그인 성공하면 HTTP Session에 Seurity Session을 생성 함 (Seurity Session = Security Context Holder)
//	Security Context Holder -> Security Context 가 있음
//	여기에 일반 로그인이면 UserDetails 타입에, 소셜 로그인이면 OAuth2User 타입에 담는다 (VO 같은거)
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		
//		userRequest에 카카오 로그인에 대한 정보가 담겨있다
		
		log.error("Token : {}",userRequest.getAccessToken());
		
		ClientRegistration registration = userRequest.getClientRegistration();
//		유저 정보가 아니라 개발자의 REST API KEY를 의미한다.
//		log.error("ClientId : {}",registration.getClientId());
		
		String sns = registration.getRegistrationId();
		
		OAuth2User aoAuth2User = super.loadUser(userRequest);
		
		if(sns.equals("kakao")) {
			aoAuth2User = this.useKakao(aoAuth2User);
		}
		
		if(sns.equals("naver")) {
			
		}
		
		return aoAuth2User;
	}
	
	private OAuth2User useKakao(OAuth2User auth2User) throws OAuth2AuthenticationException {
		
		log.error("============");
		log.error("ID:{}",auth2User.getName());
		log.error("Attributes:{}",auth2User.getAttributes());
		log.error("Authrities:{}",auth2User.getAuthorities());
		
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(auth2User.getName());

		Map<String, Object> map = (Map<String, Object>) auth2User.getAttributes().get("properties");
		memberVO.setName(map.get("nickname").toString());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_USER");
		list.add(roleVO);
		memberVO.setVos(list);
			
		return memberVO;
		
	}
	
//	인터페이스의 메서드를 오버라이딩 해서 쓰는 것이기 때문에, 메서드의 내용만 바꿀수 있지 선언부는 바꿀수가 없어!
//	그래서 내가 exception의 종류를 바꿀수가 없다. 그래서 여기서 try, catch 해줘야함
//	시큐리티 필터에서 작동하는 것 (이제 이렇게 설정해 놓으면 로그인을 하면 이 메서드가 작동!) *일반 로그인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
	
		try {
			memberVO = memberMapper.detail(memberVO);
			log.info("{}",memberVO.getUsername());
			log.info("{}",memberVO.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberVO;
	}
	
}

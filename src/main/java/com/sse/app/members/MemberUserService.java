package com.sse.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberUserService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;

//	인터페이스의 메서드를 오버라이딩 해서 쓰는 것이기 때문에, 메서드의 내용만 바꿀수 있지 선언부는 바꿀수가 없어!
//	그래서 내가 exception의 종류를 바꿀수가 없다. 그래서 여기서 try, catch 해줘야함
//	시큐리티 필터에서 작동하는 것
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

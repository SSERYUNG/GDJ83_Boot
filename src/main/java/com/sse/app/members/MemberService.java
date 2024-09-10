package com.sse.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
//	검증 메서드
	
	public boolean memberValidate(MemberVO memberVO,BindingResult bindingResult) throws Exception{
		
		//check=false : 검증성공(error 없음)
		//check=true  : 검증실패(error 있음)
		boolean check=false;
		
//		0. 기본 검증값(컨트롤러에서 Annotaion 검증의 결과값)
		check = bindingResult.hasErrors();
		
//		1. password 일치하는지 검증
		
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;
//			에러메시지 주는 법
			bindingResult.rejectValue("passwordCheck","memberVO.pw.notEqual");
		}
		
//		2. id 중복 검사
		 MemberVO result = memberMapper.detail(memberVO);
		 if(result!=null) {
			 check = true;
			 bindingResult.rejectValue("username","memberVO.username.duplication");
		 }
		
		return check;
		
	}
	
	public int add (MemberVO memberVO) throws Exception{
		
//		사용자가 회원가입할때 입력한 비밀번호를 암호화 해서 db에 집어넣는 과정
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));

		int result = memberMapper.add(memberVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("rolenum", 1);
		result = memberMapper.addRole(map);
		
		return result;
		
	}
	
	public MemberVO detail(MemberVO memberVO) throws Exception{
		
		MemberVO result = memberMapper.detail(memberVO);
		
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())){
				return result;
			}
		}
		
		return null;
	}
	

}

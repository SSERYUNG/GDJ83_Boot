package com.sse.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void passwordUpdateTest() throws Exception {
		
//		id가 manager, 비번도 manager인 애의 비번을 => 4444로 바꿔보는 작업을 해보자
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("manager");
		memberVO.setPassword("manager");
		String newPassword = "4444";
		
		MemberVO check = memberMapper.detail(memberVO);
		
		log.info("MemberVO:{}",memberVO);
		log.info("check:{}",check);
		
//		matches 함수의 매개변수 순서 잘 보고 넣기!
		if(passwordEncoder.matches(memberVO.getPassword(), check.getPassword())) {
			log.info("=== 비밀번호 일치 ===");
		}

//		암호화된 비밀번호는 equls 함수로 비교할수가 없다! 위에꺼 쓰기		
//		if(check.getPassword().equals(memberVO.getPassword())) {			
//			log.info("=== 비밀번호 일치 ===");
//		}
		
		assertEquals(check.getPassword(), memberVO.getPassword());
		
	}
	
//	@Test
	void test() throws Exception {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("manager");
		memberVO.setPassword(passwordEncoder.encode("manager"));
		
		int result = memberMapper.pwUpdate(memberVO);
		
		assertEquals(1, result);
		
	}

}

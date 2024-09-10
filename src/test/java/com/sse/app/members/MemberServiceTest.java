package com.sse.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("manager");
		memberVO.setPassword(passwordEncoder.encode("manager"));
		
		int result = memberMapper.pwUpdate(memberVO);
		
		assertEquals(1, result);
		
	}

}

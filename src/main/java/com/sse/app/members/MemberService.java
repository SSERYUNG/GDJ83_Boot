package com.sse.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public int add (MemberVO memberVO) throws Exception{

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

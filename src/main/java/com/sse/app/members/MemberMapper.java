package com.sse.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	public int add (MemberVO memberVO) throws Exception;
	
	public MemberVO detail(MemberVO memberVO) throws Exception;
	
	public int addRole(Map<String, Object> map) throws Exception;
	
}

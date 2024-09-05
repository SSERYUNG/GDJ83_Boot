package com.sse.app.members;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;

}

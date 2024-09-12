package com.sse.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sse.app.validate.MemberAddGroup;
import com.sse.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//UserDetails를 구현하면 얘가 username,password를 가져오는 getter,setter 메서드가 있음
@Data
public class MemberVO implements UserDetails,OAuth2User{
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	@Pattern(groups = {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\\\\\W)(?=\\\\\\\\S+$).{6,12}")
	@NotBlank(groups = {MemberAddGroup.class})
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	@Past(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;
	
//	OAuth2User 오버라이딩 하는 곳
	
//	여기에는 token 정보가 저장 됨
	private Map<String, Object> attributes;
	
	private String accessToken;
//	소셜 로그인 하는 곳이 어디냐(ex 카카오, 네이버)
	private String sns;
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

	//
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
		}
		
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

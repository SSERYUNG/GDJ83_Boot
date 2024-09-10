package com.sse.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 자체의 것이 아닌 우리가 만든 security 파일을 사용하겠다는 어노테이션
public class SecurityConfig {

	@Bean
	WebSecurityCustomizer webSecurityCustomizer () {
	
//		시큐리티에서 제외할 애들을 정하는거
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security.
			cors().
			and().
			csrf().
			disable();
		
		//권한에 관련된 설정
		security
		.authorizeHttpRequests(
					(authorizeRequest)->
					authorizeRequest
					.requestMatchers("/").permitAll()
					.requestMatchers("/qna/list").permitAll()
					.requestMatchers("/qna/*").authenticated()
					.requestMatchers("/notice/list","/notice/detail").permitAll()
					.requestMatchers("/notice/*").hasRole("ADMIN") //DB에서 정해놓은 ROLE_ 뒤에 단어
					.requestMatchers("/manager/*").hasAnyRole("MANAGER","ADMIN")
					.requestMatchers("/member/add","/member/login").permitAll()
					.requestMatchers("/member/*").authenticated()
					.anyRequest().permitAll()
					
		)
		
//		form login과 관련된 설정
		.formLogin(
				login ->
					login
						.loginPage("/member/login")
						.defaultSuccessUrl("/") //로그인 성공했을 때 어디루 가는지
						.failureUrl("/member/login")
//						default 이름은 username, 다른 이름으로 파라미터를 썼을 경우에는 밑에거 작성하기
//						.usernameParameter("id")
//						default 이름은 password, 다른 이름으로 파라미터를 썼을 경우에는 밑에거 작성하기
//						.passwordParameter("pw")
						.permitAll()
				)
		
		
		
		;
				
		
		return security.build();
	}
	
//	패스워드를 인코딩해서 집어넣어주는 애임
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

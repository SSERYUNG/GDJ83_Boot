package com.sse.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sse.app.members.MemberUserService;

@Configuration
@EnableWebSecurity //스프링 자체의 것이 아닌 우리가 만든 security 파일을 사용하겠다는 어노테이션
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberUserService memberUserService;

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
		
//		로그인 실패 할때 파라미터로 한글을 보내고 싶어서 설정하는거
		String message=URLEncoder.encode("로그인실패","UTF-8");
		
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
					.requestMatchers("/member/add","/member/login","/member/check").permitAll()
					.requestMatchers("/member/*").authenticated()
					.anyRequest().permitAll()
					
		)
		
//		form login과 관련된 설정
		.formLogin(
				login ->
					login
//						개발자가 만든 로그인 페이지를 사용하기 위해서 사용
						.loginPage("/member/login")
						//.defaultSuccessUrl("/") //로그인 성공했을 때 어디루 가는지
						//로그인 성공하고 추가적인 작업 및 url 이동이 필요할때
						.successHandler(handler)
//						로그인 실패했을때 어느 url로 갈지
//						.failureUrl("/member/login?message="+message)
						//로그인 살패하고 추가적인 작업 및 url 이동이 필요할때
						.failureHandler(failHandler)
//						default 이름은 username, 다른 이름으로 파라미터를 썼을 경우에는 밑에거 작성하기(vo 변수명)
//						.usernameParameter("id")
//						default 이름은 password, 다른 이름으로 파라미터를 썼을 경우에는 밑에거 작성하기(vo 변수명)
//						.passwordParameter("pw")
						.permitAll()
				)
		
//		로그아웃 관련
		.logout(
				logout ->
					logout
//					logout url을 설정(url을 String이 아니라 RequestMatcher 타입 넣으라 그러면 아래처럼 넣어주기)
//					아래의 2개의 방법
//					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
					.logoutUrl("/member/logout")
//					logout 성공시 어디로 갈 것인가
					.logoutSuccessUrl("/")
//					세션 만료시키겠다(true)
					.invalidateHttpSession(true)
//					.deleteCookies(null) <<- 로그인할 때 넣어놓은 쿠기가 있으면 삭제할수도 있음
				)
		
//		remember me(로그인 정보 저장해서 자동 로그인 되게 하는거)
		.rememberMe(
				remember->
					remember
						.rememberMeParameter("rememberMe")
//						token의 유효시간, 초 단위
						.tokenValiditySeconds(60)
//						token에 생성시 사용되는 값,필수값,개발자가 값을 설정한다(노출되면 안돼!)
						.key("rememberMe")
//						인증절차(로그인)를 진행할 UserDetailService를 설정해달라는 말
						.userDetailsService(memberUserService)
//						로그인이 성공했을 경우 진행할 Handler
						.authenticationSuccessHandler(handler)
						.useSecureCookie(false)
				
				)
//		동시 로그인(동시 세션 접속) 설정
		.sessionManagement(
				sessionManager->
					sessionManager
//						동시에 세션을 몇개까지 허용할 것인가(-1이면 무한대)
						.maximumSessions(1)
//						false라면 기존 사용자의 세션 만료, true라면 현재 접속하는 사용자가 로그인 인증 실패
						.maxSessionsPreventsLogin(false)
//						세션이 만료 됐을 경우, redirect 할 url을 적어주기
						.expiredUrl("/member/check")
				)
		
//		Social Login
		.oauth2Login(
				oauth2 ->
				 	oauth2
				 	.userInfoEndpoint(
				 			user -> user.userService(memberUserService)
				 			)
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

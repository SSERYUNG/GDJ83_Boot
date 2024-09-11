package com.sse.app.members;

import java.lang.reflect.Member;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sse.app.validate.MemberAddGroup;
import com.sse.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
//	@GetMapping("add")
//	public void add(Model model) throws Exception {
//		model.addAttribute("memberVO", new MemberVO());
//	}
	
	@GetMapping("check")
	public void check() throws Exception {
		
		
		
	}
	
	@GetMapping("add") //위에랑 똑같은 메서드임
	public void add(MemberVO memberVO) throws Exception {

	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO,BindingResult bindingResult) throws Exception {
		
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		
		if(check) {
			return "member/add";
		}
		
//		int result = memberService.add(memberVO);
		
		return "redirect:/";
		
	}
	
	@GetMapping("login")
	public void login(String message,Model model) throws Exception {
		
		model.addAttribute("message", message);
		
	}
	
//	@PostMapping("login")
//	public String login(MemberVO memberVO,HttpSession session) throws Exception {
//		
//		memberVO = memberService.detail(memberVO);
//		
//		if(memberVO!=null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:/";
//	} 
	
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		session.invalidate();
//		return "redirect:/";
//	}
	
	@GetMapping("mypage")
	public void mypage(HttpSession session) throws Exception {
		
//		세션에 담겨있는 것들을 다 꺼내서 담아보려고 함
//		1번 방법
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name : {}",name); //지금은 SPRING_SECURITY_CONTEXT 라는 속성명이 하나 있음
		}
		
		SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("obj : {}",sc);
		
//		2번 방법
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}",context);
		
//		====== 위에서 일단 session?을 꺼내놓고 그 안의 필요한 데이터들을 꺼내보자
		
		Authentication authentication = context.getAuthentication();
		log.info("authentication : {}",authentication);
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("memberVO : {}",memberVO);
		log.info("Name : {}",authentication.getName());
		
	}
	
	@GetMapping("update")
	public String update(HttpSession session,Model model) throws Exception {
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		
		return "member/update";
		
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		if(bindingResult.hasErrors()) {
			
			return "member/update";
			
		}
		
		return "redirect:./mypage";
		
	}
	
}

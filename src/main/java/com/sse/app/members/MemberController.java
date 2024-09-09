package com.sse.app.members;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
//	@GetMapping("add")
//	public void add(Model model) throws Exception {
//		model.addAttribute("memberVO", new MemberVO());
//	}
	
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
	public void login() throws Exception {
		
	}
	
	@PostMapping("login")
	public String login(MemberVO memberVO,HttpSession session) throws Exception {
		
		memberVO = memberService.detail(memberVO);
		
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
		}
		
		return "redirect:/";
	} 
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("mypage")
	public void mypage() throws Exception {
		
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

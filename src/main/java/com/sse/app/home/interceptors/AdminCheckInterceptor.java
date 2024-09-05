package com.sse.app.home.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sse.app.members.MemberVO;
import com.sse.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		int result = 0;
		 
		if(memberVO == null) {
			response.sendRedirect("/member/login");
			return false;
		}
		
		for(RoleVO r : memberVO.getVos()) {
			if(r.getRoleName().equals("ROLE_ADMIN")) {
				result = 1;
				break;
			}
		}

		if(result==1) {
			return true;
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/message.jsp");
			request.setAttribute("msg", "관리자가 아닙니다");
			request.setAttribute("url", "/");
			view.forward(request, response);
			return false;
		}
		
	}
	
}

package net.bitacademy.java41.controls.auth;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.vo.Member;

public class LoginControl implements PageControl{

	AuthService authService;

	public LoginControl setAuthService(AuthService authService) {
		this.authService = authService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String,String[]> paramMap = 
				(Map<String,String[]>)model.get("params");
		
		String email = paramMap.get("email")[0];
		String password = paramMap.get("password")[0];
	
		Member member = authService.getUserInfo(email, password);
		HttpSession session = (HttpSession)model.get("session");
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		HttpServletResponse response = (HttpServletResponse) model.get("response");
		
		if(request.getParameter("saveId") != null) {
			Cookie cookie = new Cookie("email", email);
			cookie.setMaxAge(60); 
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("email", null);
			cookie.setMaxAge(0); 
			response.addCookie(cookie);
		}
		
		if (member != null) {
			session.setAttribute("member", member);
			return "redirect:../main.do";
			
		} else {
			session.invalidate();
			return "/auth/loginFail.jsp";
		}
		
	}


	

}

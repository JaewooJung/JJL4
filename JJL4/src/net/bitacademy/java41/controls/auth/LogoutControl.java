package net.bitacademy.java41.controls.auth;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;

public class LogoutControl implements PageControl{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		
		return "redirect:../auth/loginForm.do";
	}

	
	
}

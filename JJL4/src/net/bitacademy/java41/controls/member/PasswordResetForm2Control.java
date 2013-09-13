package net.bitacademy.java41.controls.member;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

public class PasswordResetForm2Control implements PageControl{
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		return "/member/passwordForm2.jsp";
		
	}

}

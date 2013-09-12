package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.vo.Member;

public class SigninFormControl implements PageControl{

	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
				
		return "redirect:../member/signinForm.jsp";
		
	}

}

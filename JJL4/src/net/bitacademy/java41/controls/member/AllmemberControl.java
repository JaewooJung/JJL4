package net.bitacademy.java41.controls.member;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

public class AllmemberControl implements PageControl{
	MemberService memberService;
	
	public AllmemberControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {

		HttpSession session = (HttpSession) model.get("session");
		
		ArrayList<Member> member = memberService.list();
		
		if (member != null) {
			session.setAttribute("allmember", member);
			return "/member/allmember.jsp";

		
		} else {
			return "/error.jsp";
		}
		
	}

}

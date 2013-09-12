package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

public class MemberDeleteControl implements PageControl{
	MemberService memberService;
	
	public MemberDeleteControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		
		Member member = memberService.getMember(params.get("email")[0]);
		HttpSession session = (HttpSession) model.get("session");

		memberService.remove(params.get("email")[0]);
		return "../member/allmember.do";
		
	}
}

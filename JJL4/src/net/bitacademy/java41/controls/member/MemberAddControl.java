package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

public class MemberAddControl implements PageControl{
	MemberService memberService;
	
	public MemberAddControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		HttpSession session = (HttpSession) model.get("session");
		
		Member member = new Member()
		.setEmail(params.get("email")[0])
		.setName(params.get("name")[0])
		.setPassword(params.get("password")[0])
		.setTel(params.get("tel")[0])
		.setBlog(params.get("blog")[0])
		.setDetailAddress(params.get("detailAddr")[0])
		.setTag(params.get("tag")[0])
		.setLevel(Integer.parseInt(params.get("level")[0]));

		memberService.signUp(member);
		return "redirect:../member/allmember.do";
	}
}

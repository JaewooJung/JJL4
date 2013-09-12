package net.bitacademy.java41.controls.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.vo.Member;

public class SigninControl implements PageControl {
	
MemberDao memberDao;
	
	public SigninControl setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");

		Member member = new Member()
		.setEmail(params.get("email")[0])
		.setName(params.get("name")[0])
		.setPassword(params.get("password")[0])
		.setTel(params.get("tel")[0])
		.setBlog(params.get("blog")[0])
		.setDetailAddress(params.get("detailAddr")[0])
		.setTag(params.get("tag")[0])
		.setLevel(Integer.parseInt(params.get("level")[0]));
	
		memberDao.add(member);
		
		HttpSession session = (HttpSession) model.get("session");
		session.setAttribute("member", member);
		
		return "redirect:../main.do";
		
	}




}












package net.bitacademy.java41.controls;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public class MainControl implements PageControl{

	ProjectDao projectDao;
	
	public MainControl setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession)model.get("session");
		Member member = (Member)session.getAttribute("member");
		
		ArrayList<Project> project = projectDao.getProject(member.getEmail());
		session.setAttribute("member_project", project);
		
		return "/main.jsp";
	}

}

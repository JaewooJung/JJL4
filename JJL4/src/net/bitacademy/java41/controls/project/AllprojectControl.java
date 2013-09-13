package net.bitacademy.java41.controls.project;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public class AllprojectControl implements PageControl{
	ProjectService projectService;

	public AllprojectControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		
			
			HttpSession session = (HttpSession)model.get("session");
			Member member = (Member) session.getAttribute("member");
			ArrayList<Project> project = (ArrayList<Project>) projectService.getProject(); 
			ArrayList<Project> project2 = projectService.getProject(member.getEmail());
			session.setAttribute("member_project", project2);		
			if (project != null) {
				session.setAttribute("project", project);
				
				return "../project/allproject.jsp";
			} else {
				return "../error.jsp";
			}
		
	}

}

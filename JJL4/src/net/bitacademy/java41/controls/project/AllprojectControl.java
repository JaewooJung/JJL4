package net.bitacademy.java41.controls.project;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Project;

public class AllprojectControl implements PageControl{
	ProjectDao projectDao;
	
	public AllprojectControl setProjectDao(ProjectDao projectDao){
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		
			
			HttpSession session = (HttpSession)model.get("session");
			ArrayList<Project> project = projectDao.getProject();
			if (project != null) {
				session.setAttribute("project", project);
				return "../project/allproject.jsp";
			} else {
				return "../error.jsp";
			}
		
	}

}

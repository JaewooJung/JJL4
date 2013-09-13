package net.bitacademy.java41.controls.project;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Project;

public class ProjectDeleteControl implements PageControl{
	ProjectService projectService;
	
	public ProjectDeleteControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		HttpSession session = (HttpSession) model.get("session");

		projectService.remove(Integer.parseInt(params.get("pno")[0]));
		return "../project/allproject.do";
		
	}
}

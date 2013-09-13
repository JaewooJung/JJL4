package net.bitacademy.java41.controls.project;

import java.sql.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Project;

public class ProjectAddControl implements PageControl{
	ProjectService projectService;
	
	public ProjectAddControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		HttpSession session = (HttpSession) model.get("session");
		
		Project project = new Project()
						.setTitle(params.get("title")[0])
						.setContent(params.get("content")[0])
						.setStartDate(Date.valueOf(params.get("startdate")[0]))
						.setEndDate(Date.valueOf(params.get("enddate")[0]))
						.setTag(params.get("tag")[0]);

		projectService.add(project);
		return "redirect:../project/allproject.do";
	}
}

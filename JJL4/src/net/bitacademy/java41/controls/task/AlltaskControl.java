package net.bitacademy.java41.controls.task;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Task;

public class AlltaskControl implements PageControl{
	TaskService taskService;
	
	public AlltaskControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession) model.get("session");
		Map<String, String[]> params = (Map<String, String[]>)model.get("params");
		ArrayList<Task> task = (ArrayList<Task>) taskService.list(Integer.parseInt(params.get("pno")[0]));
		
		if (task != null) {
			session.setAttribute("alltask", task);
			return "/task/alltask.jsp";

		
		} else {
			return "/error.jsp";
		}
		
	}

}

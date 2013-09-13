package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.vo.Task;

public class TaskService {
	TaskDao taskDao;

	public TaskService setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
		return this;
	}
	
	public List<Task> list(int pno) throws Exception {
		return taskDao.list(pno);
	}
	
}

package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public class ProjectService {
	ProjectDao projectDao;
	MemberDao memberDao;
	
	public ProjectService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	public ProjectService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public ArrayList<Project> getProject(String email) throws Exception {
		
	return projectDao.getProject(email);
		
	}
	
	public ArrayList<Project> getProject() throws Exception {
	
		return projectDao.getProject(); 
			
	}
	
	public int add(Project project) throws Exception {
		
		return projectDao.add(project); 
			
	}
	
	public int remove(int pno) throws Exception {
			return projectDao.remove(pno); 
		}
	
	public Project getProject(int pno) throws Exception {
		return projectDao.getProject(pno); 
		
	}
	
	public int change(Project project) throws Exception {
		return projectDao.change(project); 
		
		
	}
	
	/*
	public List<Project> getProjectList() throws Exception {
		return projectDao.list();
	}
	
	public List<MemberProject> getMyProjects(String email) throws Exception {
		return projectDao.listByMember(email);
	}
	
	public Project getProject(int no) throws Exception {
		return projectDao.get(no);
	}
	
	public void addProject(Project project) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			projectDao.add(project, con);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}*/
}














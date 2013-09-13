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
	DBConnectionPool dbPool;
	MemberDao memberDao;
	
	public ProjectService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	public ProjectService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public ProjectService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public ArrayList<Project> getProject(String email) throws Exception {
		
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			ArrayList<Project> list = projectDao.getProject(email);
			con.commit();
			return list;
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
	}
	
	public ArrayList<Project> getProject() throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			ArrayList<Project> list = projectDao.getProject(); 
			con.commit();
			return list;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
	}
	
	public int add(Project project) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int a = projectDao.add(project, con); 
			con.commit();
			return a;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
	}
	
	public int remove(int pno) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int a = projectDao.remove(pno); 
			con.commit();
			return a;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
	}
	
	public Project getProject(int pno) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			Project project = projectDao.getProject(pno); 
			con.commit();
			return project;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
	}
	
	public int change(Project project) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int a = projectDao.change(project); 
			con.commit();
			return a;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
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














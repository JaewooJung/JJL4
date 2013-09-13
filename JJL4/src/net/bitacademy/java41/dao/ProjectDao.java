package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Project;

public class ProjectDao {
	DBConnectionPool conPool;
	
	public ProjectDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	
	public ProjectDao() {}
	
	public ProjectDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	public Project getProject(int pno) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_PRJS where PNO = "+ pno + ";");
			rs = stmt.executeQuery();
			if (rs.next()) {
				
				Project project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				
				return project;
			}else{
				return null;
			}
			
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
		
	}
	
	
	
	
	public ArrayList<Project> getProject(String email) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select t1.EMAIL, t1.MNAME, t1.PWD, t1.TEL, t1.BLOG, t2.LEVEL, t2.PNO, t3.TITLE, t3.CONTENT, t3.START_DATE, t3.END_DATE, t3.TAG from SPMS_MEMBS t1, SPMS_PRJMEMB t2, SPMS_PRJS t3 where t1.EMAIL = t2.EMAIL and t2.PNO = t3.PNO and t1.EMAIL = '" + email + "'"); // ? -> in-parameter
			rs = stmt.executeQuery();
			Project project = null;
			ArrayList<Project> list = new ArrayList<Project>();
			
			while (rs.next()) {
				
				project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				list.add(project);
			} 
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
		
	}
	
	public ArrayList<Project> getProject() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_PRJS"); // ? -> in-parameter
			rs = stmt.executeQuery();
			Project project = null;
			ArrayList<Project> list = new ArrayList<Project>();
			
			while (rs.next()) {
				
				project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				list.add(project);
			} 
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}		
	}
	
	public int add(Project project) throws Exception {
		Connection con = null;
		PreparedStatement projectStmt = null;
		PreparedStatement projectMemberStmt = null;
		ResultSet rs = null;
		
		try {
			con = this.conPool.getConnection();
			projectStmt = con.prepareStatement(
				"insert into SPMS_PRJS("
				+ " TITLE,CONTENT,START_DATE,END_DATE,TAG)"
				+ " values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			projectStmt.setString(1, project.getTitle());
			projectStmt.setString(2, project.getContent());
			projectStmt.setDate(3, project.getStartDate());
			projectStmt.setDate(4, project.getEndDate());
			projectStmt.setString(5, project.getTag());
			projectStmt.executeUpdate();
			
			// * 자동 생성된 PK 값 알아내기
			rs = projectStmt.getGeneratedKeys();
			if (rs.next()) {
				project.setPno( rs.getInt(1) );
			}
			
			// 2. 프로젝트의 PL을 등록한다.
			projectMemberStmt = con.prepareStatement(
					"insert into SPMS_PRJMEMB("
					+ " EMAIL,PNO,LEVEL)"
					+ " values(?,?,0)");
			projectMemberStmt.setString(1, project.getLeader());
			projectMemberStmt.setInt(2, project.getPno());
			projectMemberStmt.executeUpdate();
			
			return project.getPno();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch(Exception e) {}
			try {projectStmt.close();} catch(Exception e) {}
			try {projectMemberStmt.close();} catch(Exception e) {}
		}
	}
/*
	public List<Project> list() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Project> list = new ArrayList<Project>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select MNAME,PHONE,EMAIL from MEMBERS order by MNAME");
			
			Project m = null;
			while(rs.next()) {
				m = new Project();
				m.setName(rs.getString("MNAME"));
				m.setPhone(rs.getString("PHONE"));
				m.setEmail(rs.getString("EMAIL"));
				list.add(m);
			}
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	

	public Project get(String email) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select MNAME,PHONE,EMAIL,BLOG,AGE,REG_DATE"
					+ " from MEMBERS"
					+ " where EMAIL='" + email + "'");
			
			if (rs.next()) {
				Project project = new Project();
				project.setName(rs.getString("MNAME"));
				project.setPhone(rs.getString("PHONE"));
				project.setEmail(rs.getString("EMAIL"));
				project.setBlog(rs.getString("BLOG"));
				project.setAge(rs.getInt("AGE"));
				project.setRegDate(rs.getDate("REG_DATE"));
				return project;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
*/
	public int change(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			
			stmt = con.prepareStatement(
					"update SPMS_PRJS set TITLE=?, CONTENT=?, START_DATE=?, END_DATE=?, TAG=?, LEVEL=? where PNO = ?");
				stmt.setString(1, project.getTitle());
				stmt.setString(2, project.getContent());
				stmt.setDate(3, project.getStartDate());
				stmt.setDate(4, project.getEndDate());
				stmt.setString(5, project.getTag());
				stmt.setInt(6, project.getLevel());
				stmt.setInt(7, project.getPno());
			
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	public int remove(int pno) throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			return stmt.executeUpdate(
				"delete from SPMS_PRJS"
				+ " where PNO=" + pno);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}


	
}





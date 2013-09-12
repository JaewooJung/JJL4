package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;

public class MemberDao {
	DBConnectionPool conPool;
	
	public MemberDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public Member getMember(String email, String password) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_MEMBS "
				+ " where EMAIL=? and PWD=?"); // ? -> in-parameter
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Member m = new Member();
				m.setEmail(rs.getString("EMAIL"));
				m.setName(rs.getString("MNAME"));
				m.setPassword(rs.getString("PWD"));
				m.setTel(rs.getString("TEL"));
				m.setBlog(rs.getString("BLOG"));
				m.setRegDate(rs.getDate("REG_DATE"));
				m.setUpdateDate(rs.getDate("UPDATE_DATE"));
				m.setDetailAddress(rs.getString("DET_ADDR"));
				m.setTag(rs.getString("TAG"));
				m.setLevel(rs.getInt("LEVEL"));
				return m;
				
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
	
	public int add(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"insert into SPMS_MEMBS("
				+ " EMAIL,MNAME,PWD,TEL,"
				+ " BLOG,REG_DATE,UPDATE_DATE,DET_ADDR,TAG, LEVEL)"
				+ " values(?,?,?,?,?,now(),now(),?,?,?)");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getTel());
			stmt.setString(5, member.getBlog());
			stmt.setString(6, member.getDetailAddress());
			stmt.setString(7, member.getTag());
			stmt.setInt(8, member.getLevel());
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

	public List<Member> list() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> list = new ArrayList<Member>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select * from SPMS_MEMBS order by MNAME");
			
			Member m = null;
			while(rs.next()) {
				m = new Member();
				m.setEmail(rs.getString("EMAIL"));
				m.setName(rs.getString("MNAME"));
				m.setPassword(rs.getString("PWD"));
				m.setTel(rs.getString("TEL"));
				m.setBlog(rs.getString("BLOG"));
				m.setRegDate(rs.getDate("REG_DATE"));
				m.setUpdateDate(rs.getDate("UPDATE_DATE"));
				m.setDetailAddress(rs.getString("DET_ADDR"));
				m.setTag(rs.getString("TAG"));
				m.setLevel(rs.getInt("LEVEL"));
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

	

	public Member get(String email) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select * "
					+ " from SPMS_MEMBS"
					+ " where EMAIL='" + email + "'");
			
			if (rs.next()) {
				Member m = new Member();
				m.setEmail(rs.getString("EMAIL"));
				m.setName(rs.getString("MNAME"));
				m.setPassword(rs.getString("PWD"));
				m.setTel(rs.getString("TEL"));
				m.setBlog(rs.getString("BLOG"));
				m.setRegDate(rs.getDate("REG_DATE"));
				m.setUpdateDate(rs.getDate("UPDATE_DATE"));
				m.setDetailAddress(rs.getString("DET_ADDR"));
				m.setTag(rs.getString("TAG"));
				m.setLevel(rs.getInt("LEVEL"));
				return m;
				
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
	
	public List<Member> get(int pno) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select t1.LEVEL, t2.EMAIL, t2.BLOG, t2.DET_ADDR, t2.MNAME, t2.PWD, t2.REG_DATE, t2.TAG, t2.TEL, t2.UPDATE_DATE from SPMS_PRJMEMB t1, SPMS_MEMBS t2 where t1.EMAIL = t2.EMAIL and t1.PNO = " + pno + " order by pno");
			ArrayList<Member> list = new ArrayList<Member>(); 
			Member m = null;
			while (rs.next()) {
				m = new Member();
				m.setEmail(rs.getString("EMAIL"));
				m.setName(rs.getString("MNAME"));
				m.setPassword(rs.getString("PWD"));
				m.setTel(rs.getString("TEL"));
				m.setBlog(rs.getString("BLOG"));
				m.setRegDate(rs.getDate("REG_DATE"));
				m.setUpdateDate(rs.getDate("UPDATE_DATE"));
				m.setDetailAddress(rs.getString("DET_ADDR"));
				m.setTag(rs.getString("TAG"));
				m.setLevel(rs.getInt("LEVEL"));
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
	

	public int change(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_MEMBS set"
				+ " MNAME=?,PWD=?,TEL=?,BLOG=?,UPDATE_DATE=now(), DET_ADDR = ?, TAG=?, LEVEL=?"
				+ " where EMAIL=?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getTel());
			stmt.setString(4, member.getBlog());
			stmt.setString(5, member.getDetailAddress());
			stmt.setString(6, member.getTag());			
			stmt.setInt(7, member.getLevel());
			stmt.setString(8, member.getEmail());
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

	public int remove(String email) throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			return stmt.executeUpdate(
				"delete from SPMS_MEMBS"
				+ " where EMAIL='" + email + "'");
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int changePassword(
			String email, String oldPassword, String newPassword) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_MEMBS set"
				+ " PWD=?,UPDATE_DATE=now()"
				+ " where EMAIL=? and PWD=?");
			stmt.setString(1, newPassword);
			stmt.setString(2, email);
			stmt.setString(3, oldPassword);
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

}





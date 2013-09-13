package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public class MemberService {
	DBConnectionPool dbPool;
	MemberDao memberDao;
	
	public MemberService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public MemberService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public void signUp(Member member) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			memberDao.add(member);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
	}
	
	public List<Member> getMemberList() throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			List<Member> list = memberDao.list();
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
	
	public Member getMember(String strings) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			con.commit();
			return memberDao.get(strings);
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
	}
	
	public ArrayList<Member> list() throws Exception{
		Boolean boo = false;
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			ArrayList<Member> list = (ArrayList<Member>) memberDao.list();
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

	
	public int change(Member member) throws Exception{
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int a = memberDao.change(member);
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
	
	public boolean changePassword(String email, String oldPassword, String newPassword) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			int a = memberDao.changePassword(email, oldPassword, newPassword);
			con.commit();
			
			if(a > 0){
			return true;
			}else{
			return false;
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
	}
	
	public int remove(String email) throws Exception{
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			
			int a = memberDao.remove(email);
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
	
	
	public List<Member> get(int pno) throws Exception {
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			List<Member> list = memberDao.get(pno); 
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
	
}

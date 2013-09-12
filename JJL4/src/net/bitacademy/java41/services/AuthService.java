package net.bitacademy.java41.services;

import java.sql.Connection;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;

public class AuthService {
	ProjectDao projectDao;
	DBConnectionPool dbPool;
	MemberDao memberDao;
	
	public AuthService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public AuthService setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
		return this;
	}
	
	public Member getUserInfo(String email, String password) throws Exception {
		
		Connection con = dbPool.getConnection();
		con.setAutoCommit(false);
		try {
			Member member = (Member) memberDao.getMember(email, password);
			con.commit();
			return member;
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			dbPool.returnConnection(con);
		}
		
		
		
	}

	
}















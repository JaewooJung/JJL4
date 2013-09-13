package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Task;
import net.bitacademy.java41.vo.Task;

public class TaskDao {
DBConnectionPool conPool;
	
	public TaskDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	
	public TaskDao() {	}
	
	public TaskDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public List<Task> list(int pno) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Task> list = new ArrayList<Task>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM SPMS_TASKS where PNO=" + pno);
			
			Task t = null;
			while(rs.next()) {
				t = new Task();
				t.setTno(rs.getInt("TNO"));
				t.setPno(rs.getInt("PNO"));
				t.setTitle(rs.getString("TITLE"));
				t.setUiprotourl(rs.getString("UIPROTOURL"));
				t.setContent(rs.getString("CONTENT"));
				t.setStartDate(rs.getDate("START_DATE"));
				t.setEndDate(rs.getDate("END_DATE"));
				t.setStatus(rs.getInt("STATUS"));
				list.add(t);
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
	
}

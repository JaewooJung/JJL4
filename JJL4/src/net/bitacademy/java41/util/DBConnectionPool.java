package net.bitacademy.java41.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String dburl;
	String id;
	String pwd;
	String driverClass;
	
	ArrayList<Connection> list = new ArrayList<Connection>();
	
	public DBConnectionPool(
			String dburl, String id, String pwd, String driverClass) {
		this.dburl = dburl;
		this.id = id;
		this.pwd = pwd;
		this.driverClass = driverClass;
	}
	
	public Connection getConnection() throws Exception {
		if (list.size() > 0) {
			return list.remove(0);
		} else {
			Class.forName(driverClass);
			return DriverManager.getConnection(dburl, id, pwd);
		}
	}
	
	public void returnConnection(Connection con) {
		list.add(con);
	}
}








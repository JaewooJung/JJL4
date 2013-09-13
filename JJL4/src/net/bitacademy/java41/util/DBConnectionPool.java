package net.bitacademy.java41.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Hashtable;

public class DBConnectionPool {
	String dburl;
	String id;
	String pwd;
	String driverClass;
	
	ArrayList<Connection> list = new ArrayList<Connection>();
	Hashtable<Thread,Connection> rentTable = new Hashtable<Thread,Connection>();
	
	public DBConnectionPool() {}
	
	public DBConnectionPool(
			String dburl, String id, String pwd, String driverClass) {
		this.dburl = dburl;
		this.id = id;
		this.pwd = pwd;
		this.driverClass = driverClass;
	}
	
	public DBConnectionPool setUrl(String url) {
		this.dburl = url;
		return this;
	}
	
	public DBConnectionPool setDriver(String driver) {
		this.driverClass = driver;
		return this;
	}
	
	public DBConnectionPool setUsername(String username) {
		this.id = username;
		return this;
	}
	
	public DBConnectionPool setPassword(String password) {
		this.pwd = password;
		return this;
	}
	
	public Connection getConnection() throws Exception {
		Thread currThread = Thread.currentThread(); // 누가 빌리러 왔는지 알아낸다.
		
		Connection con = rentTable.get(currThread); // 이전에 빌려간게 있는지 확인한다.
		
		if (con != null) { // 빌린적이 있다면, 그 값을 그대로 다시 리턴한다.
			return con;
			
		} else { // 빌린적이 없다면,
			if (list.size() > 0) { // 창고에 남아도는 컨넥션이 있다면,
				con = list.remove(0); // 꺼낸다
				
			} else { // 창고가 비어 있다면 
				Class.forName(driverClass);
				con = DriverManager.getConnection(dburl, id, pwd); // 새로 만든다.
			}
			
			rentTable.put(currThread, con); // 대여 장부에 기록한다.
			return con; // 리턴한다.
		}
	}
	
	public void returnConnection(Connection con) {
		Thread currThread = Thread.currentThread(); // 누가 반납하러 왔는지 알아낸다.
		rentTable.remove(currThread); // 대여장부에서 기록을 삭제한다.
		
		list.add(con); // 창고에 넣는다.
	}
}








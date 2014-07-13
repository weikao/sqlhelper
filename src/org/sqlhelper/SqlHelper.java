package org.sqlhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlhelper.dialect.Dialect;
import org.sqlhelper.dialect.WhereGroup;

public class SqlHelper {
	private String type;
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private Dialect dialect;
	private String sql;

	public SqlHelper connection() throws Exception {
		if(this.type==null){
			System.out.println("SqlHelper Error:The 'type' has not set.");
			return null;
		}
		if(this.driver==null){
			System.out.println("SqlHelper Error:The 'driver' has not set.");
			return null;
		}
		if(this.url==null){
			System.out.println("SqlHelper Error:The 'url' has not set.");
			return null;
		}
		if(this.user==null){
			System.out.println("SqlHelper Error:The 'user' has not set.");
			return null;
		}
		if(this.password==null){
			System.out.println("SqlHelper Error:The 'password' has not set.");
			return null;
		}
		
		Class.forName(driver).newInstance();
		this.conn = DriverManager.getConnection(this.url, this.user,
				this.password);

		Dialect dialect = (Dialect) Class
				.forName("org.sqlhelper.dialect."+this.type).newInstance();
		dialect.table("abc");
		return this;
	}

	public WhereGroup whereGrpup(String op){
		WhereGroup wg = new WhereGroup(op,type);
		return wg;
	}
	
	
	public void close() throws SQLException{
		if(this.conn!=null&&this.conn.isClosed()==false){
			conn.close();
		}
	}
	
	
	public String CheckSql() {
		System.out.println("SqlHelper(print sql):" + sql);
		return sql;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void setDriver(String driver){
		this.driver = driver;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

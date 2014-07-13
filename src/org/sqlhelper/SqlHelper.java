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

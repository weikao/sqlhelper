package org.sqlhelper;

import org.sqlhelper.dialect.WhereGroup;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SqlHelper conn = new SqlHelper();
		conn.setType("MySql");
		conn.setUrl("jdbc:mysql://localhost:3306/test");
		conn.setDriver("com.mysql.jdbc.Driver");
		conn.setUser("root");
		conn.setPassword("");
		
		try {
			conn.connection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WhereGroup whereGroup = conn.whereGrpup("and");
		whereGroup.add("test","eq", 1);
		whereGroup.add("test2","eq", 3);
		whereGroup.add("test","nn");
		whereGroup.add("test","bt",12,22);
		
		System.out.println(whereGroup.getSql());
		
		

	}

}

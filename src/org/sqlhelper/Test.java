package org.sqlhelper;

import org.sqlhelper.dialect.WhereGroup;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SqlHelper conn = new SqlHelper();
		try {
			conn.connection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WhereGroup whereGroup = conn.whereGrpup("and");
		whereGroup.add("test", 1);
		
		

	}

}

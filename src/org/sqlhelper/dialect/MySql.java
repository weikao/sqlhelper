package org.sqlhelper.dialect;

import java.sql.ResultSet;

public class MySql implements Dialect {

	@Override
	public String table(String tablename) {
		// TODO Auto-generated method stub
		System.out.println(tablename);
		return tablename;
	}

	@Override
	public String where(String key, String op, Object val) {
		// TODO Auto-generated method stub
		return key+op+val;
	}

	@Override
	public String limit(String sql, int rowStart, int rowEnd) {
		// TODO Auto-generated method stub
		return null;
	}

}

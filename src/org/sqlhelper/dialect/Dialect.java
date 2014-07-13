package org.sqlhelper.dialect;

public interface Dialect {
	public String table(String tablename);
	public String where(String key,String op,Object val);
	public String limit(String sql,int rowStart,int rowEnd);
}

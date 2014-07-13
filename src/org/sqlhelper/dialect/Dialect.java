package org.sqlhelper.dialect;

import java.util.Map;

public interface Dialect {
	public String table(String tablename);
	public Map<String, Object> where(String key,String op);
	public Map<String, Object> where(String key,String op,Object val);
	public Map<String, Object> where(String key,String op,Object val1,Object val2);
	public String limit(String sql,int rowStart,int rowEnd);
}

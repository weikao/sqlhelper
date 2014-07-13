package org.sqlhelper.dialect;

import java.util.ArrayList;
import java.util.Map;

public class WhereGroup {
	public String op;
	public ArrayList<String> keys;
	public ArrayList<Object> vals;
	private String sql;
	private Dialect dialect;
	/*
	 * op=OR/AND
	 * type=MySql/SqlServer/...
	 */
	public WhereGroup(String op,String type){
		//op= OR;AND
		try {
			dialect = (Dialect) Class
					.forName("org.sqlhelper.dialect."+type).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.op = op;
	}
	
	
	public void add(String key,int val){
		add(key,(Object)val);
	}
	public void add(String key,long val){
		add(key,(Object)val);
	}
	public void add(String key,java.util.Date val){
		add(key,(Object)val);
	}
	public void add(String key,boolean val){
		add(key,(Object)((val==true)?1:0));
	}
	public void add(String key,String val){
		add(key,(Object)val);
	}
	
	public void add(String key,Object val){
		keys.add(key);
		vals.add(val);
		where.add(dialect.where(key, this.op, "?"));
	}
	public Map get(){
		
		
		return null;
	}
}

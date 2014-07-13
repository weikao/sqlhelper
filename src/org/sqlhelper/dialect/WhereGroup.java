package org.sqlhelper.dialect;
import java.util.ArrayList;
import java.util.Map;

public class WhereGroup {
	private String op;
	private ArrayList<String> keys = new ArrayList<String>();
	private ArrayList<Object> vals = new ArrayList<Object>();
	private ArrayList<String> wheres = new ArrayList<String>();
	private ArrayList<String> ops = new ArrayList<String>();
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
		this.op = op.toUpperCase();
	}
	
	
	public void add(String key,String op,int val){
		add(key,op,(Object)val);
	}
	public void add(String key,String op,long val){
		add(key,op,(Object)val);
	}
	public void add(String key,String op,java.util.Date val){
		add(key,op,(Object)val);
	}
	public void add(String key,String op,boolean val){
		add(key,op,(Object)((val==true)?1:0));
	}
	public void add(String key,String op,String val){
		add(key,op,(Object)val);
	}
	
	public void add(String key,String op){
		Map<String, Object> where = this.dialect.where(key, op);
		this.keys.add((String) where.get("key"));
		this.vals.add(where.get("val"));
		this.wheres.add((String) where.get("where"));
	}	
	public void add(String key,String op,Object val){
		Map<String, Object> where = this.dialect.where(key, op,val);
		this.keys.add((String) where.get("key"));
		this.vals.add(where.get("val"));
		this.wheres.add((String) where.get("where"));
	}	
	public void add(String key,String op,Object val1,Object val2){
		Map<String, Object> where = this.dialect.where(key, op,val1,val2);
		this.keys.add((String) where.get("key"));
		this.vals.add(where.get("val"));
		this.wheres.add((String) where.get("where"));
	}
	public String getSql(){
		String sql = "";
		for(String where:this.wheres){
			if(!sql.equals("")){
				sql += " "+ this.op +" ";
			}
			sql += where;
		}
		if(!sql.equals("")){
			sql = "("+sql+")";
		}
		return sql;
	}
	public ArrayList<String> getKeys(){
		return this.keys;
	}
	public ArrayList<String> getVals(){
		return this.keys;
	}
	public ArrayList<String> getWheres(){
		return this.wheres;
	}
	
	public void clear(){
		this.keys = new ArrayList<String>();
		this.vals = new ArrayList<Object>();
		this.wheres = new ArrayList<String>();
		this.ops = new ArrayList<String>();
	}
}

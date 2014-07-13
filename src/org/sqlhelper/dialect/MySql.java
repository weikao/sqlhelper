package org.sqlhelper.dialect;

import java.util.HashMap;
import java.util.Map;

public class MySql implements Dialect {

	public String table(String tablename) {
		// TODO Auto-generated method stub
		System.out.println(tablename);
		return tablename;
	}

	public String limit(String sql, int rowStart, int rowEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> where(String key, String op, Object val) {
		Map<String, Object> whereData = new HashMap<String, Object>();
		Map<String, String> sopt = new HashMap<String, String>();
		sopt.put("eq", "=");
		sopt.put("ne", "<>");
		sopt.put("lt", "<");
		sopt.put("le", "<=");
		sopt.put("gt", ">");
		sopt.put("ge", "<=");
		sopt.put("bw", "LIKE");
		sopt.put("bn", "NOT {key} LIKE ?%");
		sopt.put("in", "IN");
		sopt.put("ni", "NOT IN");
		sopt.put("ew", "LIKE");
		sopt.put("en", "NOT {key} LIKE %?");
		sopt.put("cn", "LIKE %?%");
		sopt.put("nc", "NOT {key} LIKE %?%");
		sopt.put("nu", "NOT NULL");
		sopt.put("nn", "IS NOT NULL");
		sopt.put("bt", "{key} BETWEEN {val1} AND {val2}");

		switch (op) {
		case "bw":
			whereData.put("key", key);
			whereData.put("val", formatVal(val) + "%");
			whereData.put("where", key + " LIKE ?");
			break;
		case "bn":
			whereData.put("key", key);
			whereData.put("val", formatVal(val) + "%");
			whereData.put("where", "NOT " + key + " LIKE ?");
			break;
		case "ew":
			whereData.put("key", key);
			whereData.put("val", "%" + formatVal(val));
			whereData.put("where", key + " LIKE ?");
			break;
		case "en":
			whereData.put("key", key);
			whereData.put("val", "%" + formatVal(val));
			whereData.put("where", "NOT " + key + " LIKE ?");
			break;
		case "cn":
			whereData.put("key", key);
			whereData.put("val", "%" + formatVal(val) + "%");
			whereData.put("where", key + " LIKE ?");
			break;
		case "nc":
			whereData.put("key", key);
			whereData.put("val", "%" + formatVal(val) + "%");
			whereData.put("where", "NOT " + key + " LIKE ?");
			break;
		default:
			whereData.put("key", key);
			whereData.put("val", formatVal(val));
			whereData.put("where", key + sopt.get(op).toString() + "?");
			break;
		}
		return whereData;
	}

	public Map<String, Object> where(String key, String op) {
		Map<String, Object> whereData = new HashMap<String, Object>();
		if (op.equals("nu") || op.equals("nn")) {
			if (op.equals("bt")) {
				whereData.put("where", key + " IS NULL");
			} else {
				whereData.put("where", "NOT " + key + " IS NULL");
			}
			whereData.put("key", key);
		}
		return whereData;
	}

	public Map<String, Object> where(String key, String op, Object val1,
			Object val2) {
		Map<String, Object> whereData = new HashMap<String, Object>();
		if (op.equals("bt") || op.equals("nb")) {
			if (op.equals("bt")) {
				whereData.put("where", "(" + key + " BETWEEN ? AND ?)");
			} else {
				whereData.put("where", "(NOT " + key + " BETWEEN ? AND ?)");
			}
			whereData.put("key", key);
			whereData.put("val", formatVal(val1));
			whereData.put("val", formatVal(val2));
		}
		return whereData;
	}

	public Map<String, Object> where(String key, String op, Object[] vals) {
		Map<String, Object> whereData = new HashMap<String, Object>();
		if (op.equals("in") || op.equals("nn")) {
			String str = "";
			for (Object val : vals) {
				if (!str.equals("")) {
					str += ",";
				}
				str += "?";
				whereData.put("val", formatVal(val));
			}
			whereData.put("key", key);
			if (op.equals("in")) {
				whereData.put("where", key + " IN (" + str + ")");
			} else {
				whereData.put("where", "NOT " + key + " IN (" + str + ")");
			}
		}
		return whereData;
	}

	private Object formatVal(Object val) {
		return val;
	}

}

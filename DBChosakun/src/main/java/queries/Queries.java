package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import Connection.ConnManager;
import util.AppProperties;

public class Queries {
	
	public List<ArrayList<String>> executeSimpleQuery(String q,int n,  Connection c) throws SQLException {
		List<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(q);
				
		while(r.next()) {
			ArrayList<String> row = new ArrayList<String>();
			for(int i = 1;i<=n;i++) {
				String t = r.getString(i);
				row.add(t);
			}
			res.add(row);
		}
		r.close();
		return res;		
	}  


	HashMap<String,List<ArrayList<String>>> allq = new HashMap<String,List<ArrayList<String>>>();
	HashMap<String,List<String>> colnames = new HashMap<String, List<String>>();
	public void executeAllQueries(Connection conn) throws SQLException {
		Properties p = AppProperties.getProperties();
		String v = p.getProperty("qry.count");
		int sz = Integer.parseInt(v);
				
		for(int i=1;i<=sz;i++) {
			String k = "qry."+i;
			String q = p.getProperty(k); if(null==q || q.trim() == null) continue;
			List<String> colnameslist = getColNames(q); if(null==colnameslist) continue;
			colnames.put(k,colnameslist);
			int c = Integer.parseInt(p.getProperty(k+".columns"));
			allq.put(k, executeSimpleQuery(q, c, conn));
		}
	}
		
	private List<String> getColNames(String q){
		int st = q.indexOf("SELECT"); 	if(-1==st) st = q.indexOf("select");
		int en = q.indexOf("FROM");		if(-1==en) en = q.indexOf("from");
		
		if(-1 == st  || -1 == en || 0 != st) return null;
		
		String[] s = q.substring(st+7, en).split(",");
		List<String> list = new ArrayList<String>();
		for(String x : s) list.add(x.trim());
		return list;
	}
	
	public HashMap<String,List<ArrayList<String>>> getAllQueryResults(){
		return allq;
	}
	
	public HashMap<String,List<String>> getAllQueryColumnNames(){
		return colnames;
	}
}

package core;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnManager;
import queries.Queries;
import util.AppProperties;
import util.OutputQueryResults;
import util.niceoutput.NiceQueryResultOutput;
import util.niceoutput.OutputQueryResultsHtml;
import util.niceoutput.QueryOutputResultsCsv;

// mvn clean compile assembly:single
public class MainCheck {
	
	public static void main(String[] args)  {
		MainCheck m = new MainCheck();
		try {
			m.doCheck();
		} catch (FileNotFoundException e) {
			System.out.println("FileIssue."+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database issue."+e.getMessage()+e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	public void doCheck() throws SQLException, FileNotFoundException {
		
		Connection c = ConnManager.getConnection();
		if(null==c) { System.out.println("connection is null!"); return;}
		
		Queries qs = new Queries();
		qs.executeAllQueries(c);
		
		NiceQueryResultOutput o ;
		String otype = AppProperties.getProperties().getProperty("qry.save.type");
		if("html".equals(otype))
			o = new OutputQueryResultsHtml(qs); 
		else
			o = new QueryOutputResultsCsv(qs);
		
		o.saveResultInAFile("Check_");
		
		c.close();
	}

}

package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

public class AppProperties {

	private static Properties p = null;
	
	static {
		InputStream is = null;
//		is = AppProperties.class.getClassLoader().getResourceAsStream("config.properties"); //for local testing.

		try { is = new FileInputStream("./config/config.properties"); } catch (FileNotFoundException e1) {System.out.println("File ./config/config.properties not found.");	}

		p = new Properties();
		try {
			p.load(is);
			parsePropertiesValue();
		} catch (IOException e) {
			System.out.println("The entries in the propertied file config.properties is not correct.");
		}finally {
			try {is.close();	} catch (IOException e) {}
		}
		
	}
	
	public static Properties getProperties() {
		return p;
	}
	
	private static void parsePropertiesValue() {
		String v = p.getProperty("qry.count"); 
		int sz = Integer.parseInt(v);
				
		for(int i=1;i<=sz;i++) {
			String k = "qry."+i;
			String q = p.getProperty(k); if(null==q || q.trim() == null) continue;
			if(0==q.indexOf("@file@")) {
				String tq = q.substring(6); 
				String tv = textFromFile(tq);  
				p.setProperty(k, tv);
			}
		}
	}
	
	private static String textFromFile(String fn) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try { br = new BufferedReader(new FileReader(fn)); } catch (FileNotFoundException e1) {System.out.println("File "+fn+" not found.");	}
		String line =null;
		try { 
			while( (  line=br.readLine() )!=null)
				if(line.indexOf("--")!=0)	sb.append(line+" "); //20221125: as the next line was getting appended with the last word of prev. line. so added space. 
		} catch (IOException e) {System.out.println("problem in reading line from "+fn);}
		
		return sb.toString();
	}
	
}

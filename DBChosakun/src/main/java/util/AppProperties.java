package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

public class AppProperties {

	private static Properties p = null;
	static {
		InputStream is = null;
//		is = AppProperties.class.getClassLoader().getResourceAsStream("config.properties");

		try { is = new FileInputStream("./config.properties"); } catch (FileNotFoundException e1) {System.out.println("File config.properties not found.");	}

		p = new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			System.out.println("The entries in the propertied file config.properties is not correct.");
		}finally {
			try {is.close();	} catch (IOException e) {}
		}
		
	}
	
	public static Properties getProperties() {
		return p;
	}
	
}

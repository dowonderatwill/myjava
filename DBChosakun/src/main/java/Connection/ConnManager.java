package Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import util.AppProperties;

public class ConnManager {
	private static HikariConfig config = new HikariConfig();
	private static DataSource ds = null;
	
	static {
		Properties p = AppProperties.getProperties();
		config.setJdbcUrl(p.getProperty("db.url"));
		config.setUsername(p.getProperty("db.uid"));
		config.setPassword(p.getProperty("db.pwd"));
		ds = new HikariDataSource(config);
		
	}
	
	private  ConnManager() {}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}

package db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBPool {
	//an instance of itself
	private static DBPool singleton = null;
	private static BasicDataSource ds;
	
	//private constructor
	private DBPool(){
		// initialize
		ds = new BasicDataSource();
		ds.setDriverClassName(DBVariables.driverClassName);
		ds.setUsername(DBVariables.user);
		ds.setPassword(DBVariables.password);
		ds.setUrl(DBVariables.url);
		
	}
	
	public static DBPool getInstance(){
		
		if(singleton == null){
			singleton = new DBPool();
		}
		return singleton;
	}
	
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}

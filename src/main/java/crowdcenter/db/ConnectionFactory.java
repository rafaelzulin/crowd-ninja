package crowdcenter.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection conn;
	
	public static Connection getConnection() throws Exception {
		if (conn == null) {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:mem:crowd_center;INIT=runscript from 'src/main/resources/crowdcenter/db/init_bd.sql'", "sa", "");
		}	
		return conn;
	}
}

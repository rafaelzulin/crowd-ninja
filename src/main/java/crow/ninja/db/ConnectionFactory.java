package crow.ninja.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection getConnection() throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem/crowd_center;INIT=runscript from 'src/main/resources/crowd/ninja/db/init_bd.sql'", "sa", "");
		return conn;
	}
}

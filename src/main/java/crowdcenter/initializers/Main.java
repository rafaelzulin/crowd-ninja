package crowdcenter.initializers;
import java.sql.Connection;
import java.sql.ResultSet;

import crowdcenter.db.ConnectionFactory;
import crowdcenter.model.User;

public class Main {

	public static void main(String[] args) throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().execute("insert into user values (1, 'Rafael Zulin');");
		
		ResultSet rs = conn.createStatement().executeQuery("select * from user;");
		while (rs.next()) {
			User user = new User(rs.getInt("id"), rs.getString("name"));
			System.out.println(user);
		}
	}
}

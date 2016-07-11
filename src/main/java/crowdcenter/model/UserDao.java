package crowdcenter.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crowdcenter.db.ConnectionFactory;

public class UserDao {

	private static Connection conn;

	public UserDao() {
		if (conn == null)
			try {
				conn = ConnectionFactory.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();

		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT id, name FROM user;");
			while (rs.next()) {
				userList.add(new User(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User getUser(int id) {
		List<User> users = getAllUsers();

		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public int addUser(User pUser) {
		List<User> userList = getAllUsers();
		boolean userExists = false;
		for (User user : userList) {
			if (user.getId() == pUser.getId()) {
				userExists = true;
				break;
			}
		}
		if (!userExists) {
			try {
				conn.createStatement()
						.execute("INSERT INTO user VALUES(" + pUser.getId() + ", '" + pUser.getName() + "')");
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				;
			}
		}
		return 0;
	}

	public int updateUser(User pUser) {
		List<User> userList = getAllUsers();

		for (User user : userList) {
			if (user.getId() == pUser.getId()) {
				try {
					conn.createStatement().execute("UPDATE USER SET id= " + pUser.getId() + ", name= '" + pUser.getName() + "' WHERE id = " + user.getId());
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int deleteUser(int id) {
		List<User> userList = getAllUsers();

		for (User user : userList) {
			if (user.getId() == id) {
				try {
					conn.createStatement().execute("DELETE USER WHERE id = "+ user.getId());
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
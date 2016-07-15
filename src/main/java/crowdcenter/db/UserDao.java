package crowdcenter.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crowdcenter.model.User;

public class UserDao extends Dao<User> {

	public UserDao() {
		super();
	}

	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();

		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT id, name, email, login, password FROM user;");
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String login = rs.getString("login");
				String password = rs.getString("password");
				userList.add(new User(id, name, email, login, password, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int add(User pUser) {
		try {
			List<User> userList = getAll();
			boolean userExists = false;
			for (User user : userList) {
				if (user.getId() == pUser.getId()) {
					userExists = true;
					break;
				}
			}

			if (!userExists) {
				String sql = "INSERT INTO user (id, name, email, login, password) " + "VALUES(" + pUser.getId() + ", '"
						+ pUser.getName() + "', '" + pUser.getLogin() + "', '" + pUser.getEmail() + "', '"
						+ pUser.getPassword() + "')";
				conn.createStatement().execute(sql);
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public User get(Long id) {
		List<User> users = getAll();

		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public int update(User pUser) {
		List<User> userList = getAll();

		for (User user : userList) {
			if (user.getId() == pUser.getId()) {
				try {
					String sql = "UPDATE USER SET name='" + pUser.getName() + "', email='" + pUser.getEmail() 
					+ "', login='" + pUser.getLogin() + "', password='" + pUser.getPassword() 
					+ "' WHERE id= " + pUser.getId();
					conn.createStatement().execute(sql);
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int delete(Long id) {
		List<User> userList = getAll();

		for (User user : userList) {
			if (user.getId() == id) {
				try {
					conn.createStatement().execute("DELETE USER WHERE id = " + user.getId());
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
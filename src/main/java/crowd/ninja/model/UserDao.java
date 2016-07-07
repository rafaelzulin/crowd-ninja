package crowd.ninja.model;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
	private static List<User> userList = null;

	public List<User> getAllUsers() {
		if (userList == null) {
			User user = new User(1, "Rafael");
			userList = new ArrayList<User>();
			userList.add(user);
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
			userList.add(pUser);
			return 1;
		}
		return 0;
	}

	public int updateUser(User pUser) {
		List<User> userList = getAllUsers();

		for (User user : userList) {
			if (user.getId() == pUser.getId()) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				return 1;
			}
		}
		return 0;
	}

	public int deleteUser(int id) {
		List<User> userList = getAllUsers();

		for (User user : userList) {
			if (user.getId() == id) {
				int index = userList.indexOf(user);
				userList.remove(index);
				return 1;
			}
		}
		return 0;
	}
}
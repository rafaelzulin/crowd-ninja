package crowdcenter.model;

import java.util.List;

import crowdcenter.db.Dao;
import crowdcenter.db.UserDao;

public class User extends Entity {

	private static Dao<User> dao;
	private String name;
	private String email;
	private String login;
	private String password;

	private User() {
		super();
		dao = new UserDao();
	}
	
	public User(String name, String email, String login, String password) {
		this();	
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public User(Long id, String name, String email, String login, String password, Boolean isNewObject) {
		//Must be used only for recover from bd
		super(false);
		this.id = id;
		this.name = name;
		this.email = email;
		this.login = login;
		this.password = password;
		this.newObject = isNewObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + "newObject= "+ newObject +"]";
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof User)) {
			return false;
		} else {
			User user = (User) object;
			if (id == user.getId() && name.equals(user.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean save() {
		int response = 0;
		if (this.isNewObject()) {
			response = dao.add(this);
			this.setNewObject(false);
		} else {
			response = dao.update(this);
		}
		return response == 1;
	}
	
	public Boolean delete() {
		if (! isNewObject()) {
			dao.delete(this.getId());
			return true;
		}
		return false;
	}
	
	public static List<User> getAll() {
		return dao.getAll();
	}
	
	public static User get(Long id) {
		return dao.get(id);
	}
}
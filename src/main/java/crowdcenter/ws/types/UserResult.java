package crowdcenter.ws.types;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import crowdcenter.model.User;

@XmlRootElement(name="user")
public class UserResult implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private Long id;
	@XmlElement
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private String login;
	
	public UserResult() {
		super();
	}

	public UserResult(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.login = user.getLogin();
	}

	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	String getLogin() {
		return login;
	}

	void setLogin(String login) {
		this.login = login;
	}
}

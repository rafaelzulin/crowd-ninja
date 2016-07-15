package crowdcenter.model;

import java.io.Serializable;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private Profile profile;

	public UserProfile() {
		super();
	}

	public UserProfile(User user, Profile profile, String level) {
		super();
		this.user = user;
		this.profile = profile;
	}

	User getUser() {
		return user;
	}

	void setUser(User user) {
		this.user = user;
	}

	Profile getProfile() {
		return profile;
	}

	void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "UserProfile [user=" + user + ", profile=" + profile + "]";
	}
}

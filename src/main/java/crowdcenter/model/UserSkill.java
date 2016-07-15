package crowdcenter.model;

import java.io.Serializable;

public class UserSkill implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private Skill skill;
	private Level level;

	public enum Level {
		LOW, MEDIUM, HIGH
	}

	public UserSkill() {
		super();
	}

	public UserSkill(User user, Skill skill, Level level) {
		super();
		this.user = user;
		this.skill = skill;
		this.level = level;
	}

	User getUser() {
		return user;
	}

	void setUser(User user) {
		this.user = user;
	}

	Skill getSkill() {
		return skill;
	}

	void setSkill(Skill skill) {
		this.skill = skill;
	}

	Level getLevel() {
		return level;
	}

	void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "UserSkill [user=" + user + ", skill=" + skill + ", level=" + level + "]";
	}
}

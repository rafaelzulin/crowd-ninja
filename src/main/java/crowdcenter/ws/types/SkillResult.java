package crowdcenter.ws.types;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import crowdcenter.model.Skill;

@XmlRootElement(name="skill")
public class SkillResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private Long id;
	@XmlElement
	private String name;
	@XmlElement
	private String description;

	public SkillResult() {
		super();
	}

	public SkillResult(Skill skill) {
		this();
		this.id = skill.getId();
		this.name = skill.getName();
		this.description = skill.getDescription();
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

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}
}

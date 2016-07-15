package crowdcenter.model;

import java.util.List;

import crowdcenter.db.Dao;
import crowdcenter.db.SkillDao;

public class Skill extends Entity {

	private static Dao<Skill> dao;
	private String name;
	private String description;

	public Skill() {
		super();
		dao = new SkillDao();
	}

	public Skill(String name, String description) {
		this();
		this.name = name;
		this.description = description;
	}
	
	public Skill(Long id, String name, String description, Boolean isNewObject) {
		//Must be used only for recover from bd
		super(false);
		this.id = id;
		this.name = name;
		this.description = description;
		this.newObject = isNewObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Skill [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (! (object instanceof Skill)) {
			return false;
		} else {
			Skill skill = (Skill) object;
			if (id == skill.getId() && name.equals(skill.getName())) {
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
	
	public static List<Skill> getAll() {
		return dao.getAll();
	}
	
	public static Skill get(Long id) {
		return dao.get(id);
	}
}

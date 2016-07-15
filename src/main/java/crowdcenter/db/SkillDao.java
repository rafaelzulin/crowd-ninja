package crowdcenter.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crowdcenter.model.Skill;

public class SkillDao extends Dao<Skill> {

	public SkillDao() {
		super();
	}

	public List<Skill> getAll() {
		List<Skill> skillList = new ArrayList<Skill>();

		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT id, name, description FROM skill;");
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				skillList.add(new Skill(id, name, description, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skillList;
	}

	public int add(Skill pSkill) {
		try {
			List<Skill> skillList = getAll();
			boolean skillExists = false;
			for (Skill skill : skillList) {
				if (skill.getId() == pSkill.getId()) {
					skillExists = true;
					break;
				}
			}

			if (!skillExists) {
				String sql = "INSERT INTO skill (id, name, description) " + "VALUES(" + pSkill.getId() + ", '"
						+ pSkill.getName() + "', '" + pSkill.getDescription() + "')";
				conn.createStatement().execute(sql);
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Skill get(Long id) {
		List<Skill> skills = getAll();

		for (Skill skill : skills) {
			if (skill.getId() == id) {
				return skill;
			}
		}
		return null;
	}

	public int update(Skill pSkill) {
		List<Skill> skillList = getAll();

		for (Skill skill : skillList) {
			if (skill.getId() == pSkill.getId()) {
				try {
					conn.createStatement().execute("UPDATE skill SET name= '" + pSkill.getName() + "', description= '"
							+ pSkill.getDescription() + "' WHERE id = " + skill.getId());
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int delete(Long id) {
		List<Skill> skillList = getAll();

		for (Skill skill : skillList) {
			if (skill.getId() == id) {
				try {
					conn.createStatement().execute("DELETE skill WHERE id = " + skill.getId());
					return 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}

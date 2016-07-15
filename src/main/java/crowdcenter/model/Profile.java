package crowdcenter.model;

import java.io.Serializable;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	private String category;
	private String description;
	
	public Profile() {
		super();
	}

	public Profile(String category, String description) {
		super();
		this.category = category;
		this.description = description;
	}

	String getCategory() {
		return category;
	}

	void setCategory(String category) {
		this.category = category;
	}

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Profile [category=" + category + ", description=" + description + "]";
	}	
}

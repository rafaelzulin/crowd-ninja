package crowdcenter.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Entity {
	
	private static Long idCount = 1L;
	private static final Logger logger = LogManager.getLogger(Entity.class);
	protected Long id;
	protected transient Boolean newObject;	
	
	public Entity() {
		super();
		initialize();
	}
	
	protected Entity(Boolean bool) {
		super();
	}
	
	protected void initialize() {
		setId(idService());
		setNewObject(true);
	}
	
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}
	
	private static Long idService() {
		logger.trace("ID generated: " + idCount);
		return idCount++;
	}
	
	public Boolean isNewObject() {
		return newObject;
	}
	
	protected void setNewObject(Boolean bool) {
		this.newObject = bool;
	}

	public abstract Boolean save();
	public abstract Boolean delete();
}

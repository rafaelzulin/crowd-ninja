package crowdcenter.db;

import java.sql.Connection;
import java.util.List;

public abstract class Dao<T> {
	
	protected Connection conn;
	
	public Dao() {
		try {
			conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public abstract List<T> getAll();

	public abstract int add(T pUser);

	public abstract T get(Long id);

	public abstract int update(T pUser);

	public abstract int delete(Long id);
}

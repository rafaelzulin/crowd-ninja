package crowdcenter.ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import crowdcenter.model.User;
import crowdcenter.model.UserDao;

@Path("/UserServices")
public class UserService {

	UserDao userDao = new UserDao();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		System.out.println("getUsers");
		return userDao.getAllUsers();
	}

	@GET
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userid") int userid) {
		System.out.println("getUser");
		return userDao.getUser(userid);
	}

	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("profession") String profession, @Context HttpServletResponse servletResponse)
			throws IOException {
		System.out.println("createUser");
		User user = new User(id, name);
		int result = userDao.addUser(user);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("profession") String profession, @Context HttpServletResponse servletResponse)
			throws IOException {
		System.out.println("updateUser");
		User user = new User(id, name);
		int result = userDao.updateUser(user);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@DELETE
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("userid") int userid) {
		System.out.println("deleteUser");
		int result = userDao.deleteUser(userid);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@OPTIONS
	@Path("/users")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSupportedOperations() {
		System.out.println("getSupportedOperations");
		return "Services\r\n" + 
			"*List users\r\n" + 
			"-> curl -X GET http://localhost:8080/rest/UserServices/users\r\n" + 
			"*Describe User\r\n" + 
			"-> curl -X GET http://localhost:8080/rest/UserServices/users/{id}\r\n" + 
			"*Create User\r\n" + 
			"-> curl -X PUT --data \"id=1&name=Margaret Thatcher\" http://localhost:8080/rest/UserServices/users\r\n" + 
			"*Update User\r\n" + 
			"-> curl -X POST --data \"id=2&name=Winston Churchill\" http://localhost:8080/rest/UserServices/users\r\n" + 
			"*Delete User\r\n" + 
			"-> curl -X DELETE http://localhost:8080/rest/UserServices/users/{id}\r\n" + 
			"*Options\r\n" + 
			"-> curl -X OPTIONS http://localhost:8080/rest/UserServices/users\r\n";
	}
}
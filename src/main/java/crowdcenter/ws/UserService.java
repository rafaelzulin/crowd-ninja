package crowdcenter.ws;

import static crowdcenter.ws.types.ResultCode.FAILURE;
import static crowdcenter.ws.types.ResultCode.SUCCESS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crowdcenter.model.User;
import crowdcenter.ws.types.Result;
import crowdcenter.ws.types.UserResult;

@Path("/UserService")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class); 
	
	@PUT
	@Path("/users")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Result createUser(
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("login") String login,
			@FormParam("password") String password) throws IOException {
		
		logger.info("createUser");
		
		User user = new User(name, email, login, password);
		
		return user.save() 
				? new Result(SUCCESS, "User was successfuly created", new UserResult(user)) 
				: new Result(FAILURE, "Create user has failed");  
	}

	@GET
	@Path("/users")
	public Result getUsers() {
		logger.info("getUsers");
		
		List<UserResult> listResult = new ArrayList<UserResult>();		
		for (User user : User.getAll()) {
			listResult.add(new UserResult(user));
		}
		
		return new Result(SUCCESS, listResult);
	}

	@GET
	@Path("/users/{id}")
	public Result getUser(@PathParam("id") Long id) {
		logger.info("getUser");
		return new Result(SUCCESS, new UserResult(User.get(id)));
	}

	@POST
	@Path("/users")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Result updateUser(
			@FormParam("id") Long id, 
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("login") String login,
			@FormParam("password") String password) throws IOException {
		logger.info("updateUser");
		
		User user = User.get(id);
		if (user == null) return new Result(FAILURE, "User not found");
		
		user.setName(name);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		
		return user.save() 
				? new Result(SUCCESS, "User was successfuly updated", new UserResult(user)) 
				: new Result(FAILURE, "Update user has failed");  
	}

	@DELETE
	@Path("/users/{userid}")
	public Result deleteUser(@PathParam("userid") Long userid) {
		logger.info("deleteUser");
		User user = User.get(userid);
		
		return user.delete() ? new Result(SUCCESS, "User was successfuly deleted") : new Result(FAILURE, "Delete user was faield");
	}
}
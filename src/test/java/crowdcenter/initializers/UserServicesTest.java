package crowdcenter.initializers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class UserServicesTest {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/rest/UserService/users";

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		UserServicesTest userServicesTest = new UserServicesTest();
		userServicesTest.init();

		userServicesTest.createUser();
		userServicesTest.getAllUsers();
		userServicesTest.getUser();
		userServicesTest.updateUser();
		userServicesTest.deleteUser();
	}

	private void createUser() {
		Form form = new Form();
		form.param("name", "Rafael Zulin");
		form.param("email", "rafaelzulin@gmail.com");
		form.param("login", "rafaelzulin");
		form.param("password", "123456");

		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println("createUserResponse: " + response);
	}

	public void getAllUsers() {
		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		System.out.println("getAllUsersResponse: " + response);
	}

	private void getUser() {
		String response = client
			.target(REST_SERVICE_URL + "/1")
			.request(MediaType.APPLICATION_JSON)
			.get(String.class);
		
		System.out.println("getUser: " + response);
	}
	
	private void updateUser() {
		Form form = new Form();
		form.param("id", "1");
		form.param("name", "Pedro Zulin");
		form.param("email", "pedrozulin@gmail.com");
		form.param("login", "pedrozulin");
		form.param("password", "123456");

		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println("updateUserResponse: " + response);
	}
	
	private void deleteUser() {
		String response = client
				.target(REST_SERVICE_URL + "/1")
				.request(MediaType.APPLICATION_JSON)
				.delete(String.class);

		System.out.println("deleteUserResponse: " + response);
	}
}

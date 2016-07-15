package crowdcenter.initializers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class SkillServicesTest {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/rest/SkillService/skills";

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		SkillServicesTest skillServicesTest = new SkillServicesTest();
		skillServicesTest.init();

		skillServicesTest.create();
		skillServicesTest.getAll();
		skillServicesTest.get();
		skillServicesTest.update();
		skillServicesTest.delete();
	}

	private void create() {
		Form form = new Form();
		form.param("name", "Java");
		form.param("description", "Skills with Java plataform");

		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println("createSkillResponse: " + response);
	}

	public void getAll() {
		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		System.out.println("getAllSkillResponse: " + response);
	}

	private void get() {
		String response = client
			.target(REST_SERVICE_URL + "/1")
			.request(MediaType.APPLICATION_JSON)
			.get(String.class);
		
		System.out.println("getSkillResponse: " + response);
	}
	
	private void update() {
		Form form = new Form();
		form.param("id", "1");
		form.param("name", "PHP");
		form.param("description", "Skills with PHP");

		String response = client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		System.out.println("updateSkillResponse: " + response);
	}
	
	private void delete() {
		String response = client
				.target(REST_SERVICE_URL + "/1")
				.request(MediaType.APPLICATION_JSON)
				.delete(String.class);

		System.out.println("deleteSkillResponse: " + response);
	}
}

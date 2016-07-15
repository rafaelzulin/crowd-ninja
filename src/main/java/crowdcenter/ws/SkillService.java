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

import crowdcenter.model.Skill;
import crowdcenter.ws.types.Result;
import crowdcenter.ws.types.SkillResult;

@Path("SkillService")
@Produces(MediaType.APPLICATION_JSON)
public class SkillService {
	
	public static final Logger logger = LogManager.getLogger(SkillService.class);
	
	@PUT
	@Path("skills")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Result createSkill(
			@FormParam("name") String name,
			@FormParam("description") String description) {
		logger.info("createSkill");
			
		Skill skill = new Skill(name, description);

		return skill.save() 
				? new Result(SUCCESS, "Skill was successfuly created", new SkillResult(skill)) 
				: new Result(FAILURE, "Create skill has failed"); 
	}
	
	@GET
	@Path("skills")
	public Result getSkills() {
		logger.info("getSkills");
		
		List<SkillResult> listResult = new ArrayList<SkillResult>();		
		for (Skill skill : Skill.getAll()) {
			listResult.add(new SkillResult(skill));
		}
		
		return new Result(SUCCESS, listResult);
	}
	
	@GET
	@Path("skills/{id}")
	public Result getSkill(@PathParam("id") Long id) {
		logger.info("getSkill");
		return new Result(SUCCESS, new SkillResult(Skill.get(id)));
	}
	
	@POST
	@Path("skills")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Result updateUser(
			@FormParam("id") Long id, 
			@FormParam("name") String name,
			@FormParam("description") String description) throws IOException {
		logger.info("updateSkill");
		
		Skill skill = Skill.get(id);
		if (skill == null) return new Result(FAILURE, "Skill not found");
		
		skill.setName(name);
		skill.setDescription(description);
		
		return skill.save() 
				? new Result(SUCCESS, "Skill was successfuly updated", new SkillResult(skill)) 
				: new Result(FAILURE, "Update skill has failed");  
	}
	
	@DELETE
	@Path("skills/{id}")
	public Result deleteUser(@PathParam("id") Long id) {
		logger.info("deleteSkill");
		Skill skill = Skill.get(id);
		
		return skill.delete() 
				? new Result(SUCCESS, "Skill was successfuly deleted") 
				: new Result(FAILURE, "Delete skill has failed");
	}
}

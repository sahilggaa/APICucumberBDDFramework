package resources;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinition.AddPlaceStepDefinition;

public class Hooks {
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		
		System.out.println("Hook Running");
		AddPlaceStepDefinition adp = new AddPlaceStepDefinition();
		if(AddPlaceStepDefinition.place_id==null)		
		{
		adp.add_place_payload_with("Rohini", "New Delhi", "Hindi");
		
		adp.user_calls_with_post_http_request("AddPlaceAPI", "Post");
		
		adp.verify_that_place_id_is_created_for_using("Rohini", "GetPlaceAPI");
	
		}
	}

}

package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;


public class AddPlaceStepDefinition extends Utils {
	
	
	ResponseSpecification respec;
	RequestSpecification res ;
	Response response;
	JsonPath js;
	
	public static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language)throws IOException {		
		res = given().spec(requestSpecification()).body(TestDataBuild.addPlace(name, address, language));
		
		
	}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		
		APIResource resourceAPI = APIResource.valueOf(resource);
		
		System.out.print(resourceAPI);
		
		respec=	new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		
		System.out.println(response);
		
	}
	
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_code(int value) {		
		
		assertEquals(response.getStatusCode(), 200); //Coming from org.junit.Assert.*
	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue)
	{
	

		System.out.println("Response Body: " + response);
		

		assertEquals(getJsonPath(response.asString(), keyValue), getJsonPath(response.asString(),keyValue));
		
		
	}
	
	
	@Then("verify that place_id is created for {string} using {string}")
	public void verify_that_place_id_is_created_for_using(String expectedName , String resource) throws IOException {
	    
		place_id =getJsonPath(response.asString(),"place_id");
		System.out.println("Place_id created: "+place_id);
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		System.out.println(place_id);
		user_calls_with_post_http_request(resource, "GET");
		String actualName = getJsonPath(response.asString(), "name");
		assertEquals(actualName,expectedName);
		
	}
	
	
	
	//Delete Place
	
	@Given("Delete Place Payload with {string}")
	public void delete_place_payload_with(String string) throws IOException {
		
		System.out.println("place_id to be deleted: "+place_id);
		res = given()
		        .spec(requestSpecification())
		        .body(TestDataBuild.getPlaceId(place_id));
	}
	@When("delete api is hit with {string} with {string} http request")
	public void delete_api_is_hit_with_with_http_request(String resource, String method) {
		
		APIResource resourceAPI_delete = APIResource.valueOf(resource);
	    response = res.when().post(resourceAPI_delete.getResource());
	    
	    System.out.println("Response for delete: "+response.asString());
	}
	@Then("status in response is {string}")
	public void status_in_response_is(String expectedValue) {
	    assertEquals(getJsonPath(response.asString(), "status"),expectedValue);
	}





  
}
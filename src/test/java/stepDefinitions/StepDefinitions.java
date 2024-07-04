package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;

public class StepDefinitions extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	@Given("AppPlace Api Payload with {string} {string} {string}")
	public void app_place_api_payload_with(String name, String language, String address) throws IOException{
		
		
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
				
			
	}
	
	@When("user call {string} Api with http {string} request")
	public void httpPostRequest(String apiResource, String httpMethod) {
		
		APIResources resourceApi = APIResources.valueOf(apiResource);
		
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	
		
		if(httpMethod.equalsIgnoreCase("POST")){
			response = res.when().post(resourceApi.getResource()).
					then().spec(resp).extract().response();					
		}
		else if(httpMethod.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceApi.getResource()).
					then().spec(resp).extract().response();
		}
		
		
	}
	
	@Then("the Api call is success with staus code {int}")
	public void validatingApiResponseCode(Integer int1) {
		
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@And("{string} in response body is {string}")
	public void responseBody(String keyValue, String expectedValue) {
		
		String actValue = getJsonPath(response, keyValue).toString();
		assertEquals(actValue, expectedValue);
		
	}
	
	@Then("verify the place id of the maps with {string} using {string} request")
	public void verify_the_place_id_of_the_maps_with_using_request(String expName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		placeId = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		httpPostRequest(resource, "GET");
		
		String actName = getJsonPath(response, "name");
		assertEquals(actName, expName);
	    
	}
	
	@Given("DeletePace Api payload")
	public void deletePlaceApiPayload() throws IOException {
		
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
		
	}


	
	
}

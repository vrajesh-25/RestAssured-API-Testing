package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		StepDefinitions steps = new StepDefinitions();
		
		if(StepDefinitions.placeId==null) {
			
			steps.app_place_api_payload_with("Roobek", "Spanish", "69 Street, Hyd");
			steps.httpPostRequest("AddPlaceApi", "POST");
			steps.verify_the_place_id_of_the_maps_with_using_request("Roobek", "getPlaceApi");
			
		}
	}

}

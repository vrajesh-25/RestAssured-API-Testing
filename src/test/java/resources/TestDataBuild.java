package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.BodyResp;
import pojo.Location;

public class TestDataBuild {
	
	public BodyResp addPlacePayload(String name, String lang, String address) {
		
		BodyResp jsonBody = new BodyResp();
		jsonBody.setAccuracy(50);
		jsonBody.setAddress(address);
		jsonBody.setLanguage(lang);
		jsonBody.setName(name);
		jsonBody.setPhone_number("(+91) 983 893 3937");
		jsonBody.setWebsite("http://google.com");
		
		Location loc = new Location();
		loc.setLng(-38.383494);
		loc.setLat(33.427362);
		jsonBody.setLocation(loc);
		
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		jsonBody.setTypes(myList);
		
		return jsonBody;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{ \r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeId+"\" \r\n"
				+ "\r\n"
				+ "} ";
	}

}

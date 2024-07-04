Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being added using AddPlace Api

	Given AppPlace Api Payload with "<name>" "<language>" "<adress>"
	When user call "AddPlaceApi" Api with http "POST" request
	Then the Api call is success with staus code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify the place id of the maps with "<name>" using "getPlaceApi" request
		

Examples:

	|name       |language|adress           |
	|Paradise   |English |Fifth, Steet, Hyd|
	

@DeletePlace
Scenario: Verify if place is being deleted using delete place Api

	Given DeletePace Api payload
	When user call "deletePlaceApi" Api with http "POST" request
	Then the Api call is success with staus code 200
	And "status" in response body is "OK"
	
	
	


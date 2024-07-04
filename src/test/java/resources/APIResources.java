package resources;

public enum APIResources {
	
	AddPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	
	private String resource;

	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getResource(){
		return resource;
	}

}

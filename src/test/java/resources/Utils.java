package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification request;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(request==null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
			return request;
			
		}
		
		return request;
		
		
	}
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fileInput = new FileInputStream("C:\\Users\\RAJESH\\eclipse-workspace\\EtoEApiTest\\CucumberFmTest\\src\\test\\java\\resources\\global.properties");
		prop.load(fileInput);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String result = response.asString();
		JsonPath js = new JsonPath(result);
		return js.get(key).toString();
		
	}

}

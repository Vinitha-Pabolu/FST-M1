package examples;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Project {
	// Declare Request and Response specifications objects
	String sshKey = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIOcpqVJCP6juPVYz99/ZMrC96zbc7JhiOuBbSNnsPOw1";
	RequestSpecification RequestSpec;
	ResponseSpecification ResponseSpec;
	int keyId;
	
		@BeforeClass
		public void setUp() {
			// Create request specification
			RequestSpec = new RequestSpecBuilder().
					setBaseUri("https://api.github.com/user/keys").
					addHeader("Content-Type","application/json").
					addHeader("Authorization", "Bearer ").
					addHeader("X-GitHub-Api-Version", "2022-11-28").
					build();
			
			// Create a response Specification
			ResponseSpec = new ResponseSpecBuilder().
					expectBody("title",Matchers.equalTo("TestKey")).
					expectBody("key", Matchers.equalTo(sshKey)).
					expectResponseTime(lessThanOrEqualTo(5000L)).
					build();
		}
		
		@Test(priority = 1)
		//POST 
		public void postRequestTest() {
			Map <String, String> reqBody = new HashMap<>();
			reqBody.put("title", "TestKey");
			reqBody.put("key", sshKey);
			// Send request and save the response
			Response response = given().spec(RequestSpec).body(reqBody).
								when().post();
			// Extract Id from the response
			this.keyId = response.then().extract().path("id");
			// Assertions
			response.then().spec(ResponseSpec);
			
		}
		//GET 
		@Test(priority = 2)
		public void getRequestTest() {
			// Send request, get request, Assert
			given().spec(RequestSpec).pathParam("keyId",this.keyId).
			when().get("/{keyId}").
			then().spec(ResponseSpec).body("title",equalTo("TestKey"));
			
		}
		//DELETE
		@Test(priority = 3)
		public void deleteRequestTest() {
			// Send request, get request, Assert
			given().spec(RequestSpec).pathParam("KeyId",this.keyId).
			when().delete("/{KeyId}").
			then().statusCode(204);
			
		}
}

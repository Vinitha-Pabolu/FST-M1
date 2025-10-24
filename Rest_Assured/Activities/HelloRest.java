package examples;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HelloRest {
	String baseURI;

	@Test
	public void helloRest() {
		baseURI = "https://petstore.swagger.io/v2/pet";

		Response response = given().contentType(ContentType.JSON).when().get(baseURI + "/findByStatus?status=sold");
		response.prettyPrint();

		response.then().statusCode(200);
		response.then().body("[0].status", equalTo("sold"));
	}

}


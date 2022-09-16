package chain;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateContact extends TestNGSetup{
	
	@Test
	public void createContact() {
		String inputbody="{\r\n"
				+ "        \"FirstName\": \"Ganesh\",\r\n"
				+ "        \"LastName\": \"Krishnamoorthy\"\r\n"
				+ "    }";
		
		inputRequest.given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON).body(inputbody);
		response= inputRequest.when().post();
		response.then().assertThat().statusCode(201);
		contact_ID=response.jsonPath().getString("id");
		System.out.println("Created Contactid="+contact_ID);
		
		
		
	}

}

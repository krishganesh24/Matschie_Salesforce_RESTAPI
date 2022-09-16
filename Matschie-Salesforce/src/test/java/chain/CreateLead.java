package chain;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateLead  extends TestNGSetup{
	
	
	@Test
	public void createLead (){
		
		String reqbody ="{\r\n"
				+ "        \"FirstName\": \"RestAPI\",\r\n"
				+ "        \"LastName\": \"RestAssured\",\r\n"
				+ "        \"Company\": \"InfosysLed\"  \r\n"
				+ "    }";
		
		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON). body(reqbody);
		response=inputRequest.when().post();
		response.then().assertThat().statusCode(201);
		lead_ID=response.jsonPath().getString("id");
		System.out.println("Leadid="+lead_ID);
		long responsetime =response.getTime();
		System.out.println("Reponse Time="+responsetime);
		
		if (responsetime<500) {
			System.out.println("Response time is less than 500ms");
		}
		else {
			
			System.out.println("Response time is Greater than 500ms");
			
		}	
	

}
	
}

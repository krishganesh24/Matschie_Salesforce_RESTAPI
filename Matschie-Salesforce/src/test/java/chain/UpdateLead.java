package chain;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UpdateLead extends TestNGSetup{
	
	
	
	@Test(dependsOnMethods="chain.CreateLead.createLead")
	public void updateLead() {
		
		String reqbody ="{\"Country\":\"India\",\r\n"
				+ "\"Company\": \"Wipro Ltd\"}";
		
		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON). body(reqbody);
		response=inputRequest.when().patch(lead_ID);
		response.then().assertThat().statusCode(204);
			
	}

}

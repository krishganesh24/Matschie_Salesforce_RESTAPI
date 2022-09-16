package chain;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UpdateContact extends TestNGSetup{
	
	
	
	@Test(dependsOnMethods="chain.CreateContact.createContact")
	public void updateContact() {
		
		String reqbody ="{\r\n"
				+ "        \"MailingState\": \"TamilNadu\"\r\n"
				+ "           }";
		
		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON). body(reqbody);
		response=inputRequest.when().patch(contact_ID);
		response.then().assertThat().statusCode(204);
			
	}

}

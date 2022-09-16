package chain;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetContact extends TestNGSetup {

	@Test(dependsOnMethods = "chain.CreateContact.createContact")
	public void getContact() {

		inputRequest.given().log().all();
		response = inputRequest.when().get(contact_ID);
		response.then().assertThat().statusCode(200);
		response.then().assertThat().body("Id",Matchers.equalTo(contact_ID));
		response.then().assertThat().body("Name", Matchers.containsString("Ganesh Krishnamoorthy"));
		response.then().assertThat().body("FirstName", Matchers.containsString("Ganesh"));
		response.then().assertThat().body("LastName", Matchers.containsString("Krishnamoorthy"));
		String name=response.jsonPath().getString("Name");
		System.out.println(name);
		if (name.equals("Ganesh Krishnamoorthy")){
			
			System.out.println("Name retrived Successfully");
			
		}
		
	}
}

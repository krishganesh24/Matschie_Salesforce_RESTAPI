package chain;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetLead extends TestNGSetup {

	@Test(dependsOnMethods = "chain.CreateLead.createLead")
	public void getLead() {

		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		response = inputRequest.when().get();
		response.then().assertThat().statusCode(200);

		List<Object> leadlist = response.jsonPath().getList("recentItems.Id");
		System.out.println(leadlist);

		if (leadlist.contains(lead_ID)) {

			System.out.println("Created Lead id is available in the Lead list");

		}
		
		else {
			
			System.out.println("Created Lead id is NOT available in the Lead list");

			
		}

	}

}

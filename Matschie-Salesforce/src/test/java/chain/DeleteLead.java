package chain;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DeleteLead extends TestNGSetup {

	@Test(dependsOnMethods = "chain.CreateLead.createLead")
	public void deleteLead() {

		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		response = inputRequest.when().delete(lead_ID);
		response.then().assertThat().statusCode(204);

	}

	@Test
	public void validateDeletedLead() {
		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		response = inputRequest.when().get();
		response.then().assertThat().statusCode(200);
		List<Object> leadlist = response.jsonPath().getList("recentItems.Id");
		System.out.println(leadlist);
		if (!leadlist.contains(lead_ID)) {
			System.out.println("Created Lead id is not available in the Lead list & Deleted from DB");
		} else {
			System.out.println("Created Lead id is available in the Lead list");
		}
	}
}

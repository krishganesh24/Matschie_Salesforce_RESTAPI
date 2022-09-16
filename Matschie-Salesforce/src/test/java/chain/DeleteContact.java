package chain;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DeleteContact extends TestNGSetup {

	@Test(dependsOnMethods = "chain.CreateContact.createContact")
	public void deleteContact() {

		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		response = inputRequest.when().delete(contact_ID);
		response.then().assertThat().statusCode(204);

	}

	@Test
	public void validateDeletedContact() {
		inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		response = inputRequest.when().get();
		response.then().assertThat().statusCode(200);
		List<Object> Contactlist = response.jsonPath().getList("recentItems.Id");
		System.out.println(Contactlist);
		if (!Contactlist.contains(contact_ID)) {
			System.out.println("Created Contact id is not available in the Contact list & Deleted from DB");
		} else {
			System.out.println("Created Contact id is available in the Contact list");
		}
	}
}

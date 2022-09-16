package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRestImpl {

	public static RequestSpecification inputRequest;
	public static String lead_ID;
	public static Response response;
	public static String contact_ID;


	public static void preConfig() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));

		if (prop.getProperty("module").equalsIgnoreCase("Lead")) {

			RestAssured.baseURI = "https://" + prop.getProperty("server") + "/" + prop.getProperty("resources") + "/"
					+ prop.getProperty("table_name1");
		} else if (prop.getProperty("module").equalsIgnoreCase("Contact")) {

			RestAssured.baseURI = "https://" + prop.getProperty("server") + "/" + prop.getProperty("resources") + "/"
					+ prop.getProperty("table_name2");

		}
		if (prop.getProperty("Authentication").equalsIgnoreCase("Basic")) {
			inputRequest = RestAssured.given().log().all().auth().basic(prop.getProperty("username"),
					prop.getProperty("password"));
		}

		else if (prop.getProperty("Authentication").equalsIgnoreCase("OAuth2")) {
			String tokenURL = "https://login.salesforce.com/services/oauth2/token";
			RequestSpecification inputreq = RestAssured.given().log().all()
					.contentType("application/x-www-form-urlencoded").formParam("grant_type", "password")
					.formParam("client_id",
							"3MVG9wt4IL4O5wvKCwDTaM4zhqMVdGtVPU6W1qJ3X5Kyku873xnLFYFaRqO9654Aja.cDeqnFnWROJ7I6UdSR")
					.formParam("client_secret", "34469143ADA2270B45DC179BD6BF00BEA56E6A0F108718F9BFB6CCA3E300A043")
					.formParam("username", "apitestingjuly@testleaf.com").formParam("password", "Testleaf123");

			Response resp = inputreq.post(tokenURL);
			String token = resp.jsonPath().getString("access_token");
			System.out.println("Created token=" + token);
			inputRequest = RestAssured.given().log().all().auth().oauth2(token);
			System.out.println("OAuth2 Authetication Succesful");
		}

	}

	public static void postConfig() {
		response.then().log().all();
	}

}

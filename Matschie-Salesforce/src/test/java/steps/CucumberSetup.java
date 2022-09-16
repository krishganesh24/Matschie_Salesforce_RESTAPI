package steps;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.BaseRestImpl;

public class CucumberSetup extends BaseRestImpl {
	
	@Before
	public void before() throws FileNotFoundException, IOException {
		preConfig();
	}
	
	@After
	public void after() {
		postConfig();
	}

}

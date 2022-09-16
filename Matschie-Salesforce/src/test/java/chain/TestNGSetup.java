package chain;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import utils.BaseRestImpl;

public class TestNGSetup extends BaseRestImpl {
	
	@BeforeSuite
	public void before() throws FileNotFoundException, IOException {
		preConfig();
	}
	
	@AfterMethod
	public void after() {
		postConfig();
	}

}

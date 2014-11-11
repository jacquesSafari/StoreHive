package testCases.serviceTests;

import junit.framework.Assert;
import main.java.com.storehive.application.services.app.BusinessServices;
import main.java.com.storehive.application.services.app.impl.BusinessServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginClientTestCase {
	
	private static BusinessServices bss;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bss = new BusinessServicesImpl();
		
	}

	@Test
	public void runTests() {
		loginTFUsernameDontExist();
		loginTFUsernamePasswordIncorrect();
		loginTPUsernameAndPasswordCorrect();
	}
	
	private void loginTFUsernameDontExist(){
		JSONObject clientToLogIn = new JSONObject();
		clientToLogIn.put("username", "tyrones");
		clientToLogIn.put("password", "1234568789");
		ResponseResult output = bss.loginClient(clientToLogIn);
		Assert.assertEquals(false, output.isSuccessful());
		Assert.assertEquals("The username provided does not exist.",output.getErrorMessage());
	}

	private void loginTFUsernamePasswordIncorrect(){
		JSONObject clientToLogIn = new JSONObject();
		clientToLogIn.put("username", "tyrone");
		clientToLogIn.put("password", "1234568789s");
		ResponseResult output = bss.loginClient(clientToLogIn);
		Assert.assertEquals(false, output.isSuccessful());
		Assert.assertEquals("The password provided is incorrect.",output.getErrorMessage());
	}
	
	private void loginTPUsernameAndPasswordCorrect(){
		JSONObject clientToLogIn = new JSONObject();
		clientToLogIn.put("username", "tyrone");
		clientToLogIn.put("password", "1234568789");
		ResponseResult output = bss.loginClient(clientToLogIn);
		Assert.assertEquals(true, output.isSuccessful());
	}
}

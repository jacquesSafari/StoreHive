package main.java.com.storehive.tests.services;

import main.java.com.storehive.application.services.app.BusinessServices;
import main.java.com.storehive.application.services.app.impl.BusinessServicesImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegisterStoreOwnerTestService {

	private static BusinessServices bss;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bss = new BusinessServicesImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void createStoreOwnerTestPass(){
//		JsonObject client = new JsonObject();
//		client.put("name", "Tyrone");
//		client.put("surname", "Adams");
//		client.put("username", "labamba");
//		client.put("password", "1234568789");
//		client.put("registrationDate",new DateTime().toString());
//		
//		Assert.assertTrue("labamba".equals(bss.registerClient(client)));
	}

}

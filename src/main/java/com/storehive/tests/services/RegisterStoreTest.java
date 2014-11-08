package main.java.com.storehive.tests.services;

import java.net.UnknownHostException;

import main.java.com.storehive.application.services.app.StoreOperationServices;
import main.java.com.storehive.application.services.app.impl.StoreOperationServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class RegisterStoreTest {

	private StoreOperationServices ss;
	
	public RegisterStoreTest()throws UnknownHostException {
		ss = new StoreOperationServicesImpl();
	}
	
	@Test
	public void testStoreCreation() {
		JSONObject j = new JSONObject();
		JSONObject l = new JSONObject();
		l.put("latitude", "123.0");
		l.put("longitude", "-123.5");
		j.put("shopName", "Peters Fashion");
		j.put("description", "High End Fashion at Low End Prices");
		j.put("lastOpenedDate", DateTime.now().toString());
		j.put("ownerEmail", "tyrondms@gmail.com");
		j.put("isOpen", false);
		j.put("location", l);
		
		ResponseResult r = ss.registerStore(j);
		
		Assert.assertTrue(r.isSuccessful());
		Assert.assertEquals("tyrondms@gmail.com", r.getErrorMessage());
	}

}

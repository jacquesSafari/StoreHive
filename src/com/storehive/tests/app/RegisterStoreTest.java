package com.storehive.tests.app;

import java.net.UnknownHostException;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.storehive.app.services.app.StoreOperationServices;
import com.storehive.app.services.app.impl.StoreOperationServicesImpl;
import com.storehive.app.utilities.ResponseResult;

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

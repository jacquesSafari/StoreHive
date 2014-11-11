package testCases.crudTests;

import junit.framework.Assert;
import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;

public class StoreOwnerCRUDTest {
	
	private static StoreOwnerCrudService crudService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		crudService = new StoreOwnerCrudServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void runTests(){
		createStoreOwnerTestPass();
		createStoreOwnerHandleFail();
	}
	
	private void createStoreOwnerTestPass(){
		BasicDBObject details = new BasicDBObject();
		details.put("name", "Tyrone");
		details.put("surname", "Adams");
		details.put("username", "tyrone");
		details.put("password", "1234568789");
		details.put("deviceID", "aaaa");
		details.put("registrationDate",new DateTime().toString());
		
		StoreOwner a = ApplicationFactory.buildStoreOwner(details);
		ResponseResult output = crudService.createEntity(a);
		Assert.assertEquals(true, output.isSuccessful());
	}
	
	private void createStoreOwnerHandleFail(){
		BasicDBObject details = new BasicDBObject();
		details.put("name", "Tyrone");
		details.put("surname", "Adams");
		details.put("username", "tyrone");
		details.put("password", "1234568789");
		details.put("deviceID", "aaaa");
		details.put("registrationDate",new DateTime().toString());
		
		StoreOwner a = ApplicationFactory.buildStoreOwner(details);
		ResponseResult output = crudService.createEntity(a);
		Assert.assertEquals(false, output.isSuccessful());
	}
}

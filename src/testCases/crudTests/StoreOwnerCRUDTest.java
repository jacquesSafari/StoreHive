package testCases.crudTests;

import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.Utilities;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;

public class StoreOwnerCRUDTest {
	
	private static StoreOwnerCrudService crudService;
	private StoreOwner a;
	private static Morphia morphia; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		crudService = new StoreOwnerCrudServiceImpl();
		morphia = new Morphia();
		morphia.mapPackage(Utilities.DOMAIN_CLASS_PATH);
	}

	@Test
	public void runTests(){
		createStoreOwner();
		readStoreOwner();
		readAllStoreOwners();
		updateStoreOwner();
		deleteStoreOwner();
	}
	
	private void createStoreOwner(){
		BasicDBObject details = new BasicDBObject();
		details.put("fullname", "Tyrone Adams");
		details.put("email", "tyrone@gmail.com");
		details.put("password", "1234568789");
		details.put("deviceId", "aaaa");
		details.put("registrationDate",new DateTime().toString());
		
		a = 
		ResponseResult output = crudService.createEntity(a);
		Assert.assertEquals(true, output.isSuccessful());
	}
	
	private void readStoreOwner(){
		Assert.assertEquals("Tyrone Adams",crudService.findStoreOwnerByEmail(a.getEmail()).getFullname());
	}
	
	private void readAllStoreOwners(){
		Assert.assertTrue(crudService.getAllEntities().size()>0);
	}

	private void updateStoreOwner(){
		//TODO: Need to still assess how to perform the opertation
	}
	
	private void deleteStoreOwner(){
		crudService.deleteEntity(a);
		
		Assert.assertNull(crudService.findStoreOwnerByEmail(a.getEmail()));
	}
}

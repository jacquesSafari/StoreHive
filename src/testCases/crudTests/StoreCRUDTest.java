package testCases.crudTests;

import java.net.UnknownHostException;

import main.java.com.storehive.application.domain.Location;
import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.junit.Assert;
import org.junit.Test;

public class StoreCRUDTest {

	private StoreCrudServices scs;
	private Store store;
	
	public StoreCRUDTest() throws UnknownHostException{
		scs = new StoreCrudServicesImpl();
	}
	
	@Test
	public void runAllTests() {
		createStoreTest();
		findStoreTest();
		findAllStoresTest();
		updateStoresTest();
		deleteStoresTest();
	}

	//preconditions - need a store object that contains correct values
	//postconditions - need to return a response result object, with a isSuccessful = true
	private void createStoreTest(){ 
		Location l = new Location();
		l.setLatitude("123.4");
		l.setLongitude("123.0");
		
		Store s = new Store();
		s.setOwnerEmail("tyrondms@gmail.com");
		s.setShopName("Tyrones Kos Winkel");
		s.setDescription("we sell food here");
		s.setLocation(l);
		
		ResponseResult r = scs.createEntity(s);
		store = scs.findStoreByProperty("ownerEmail", s.getOwnerEmail());
		
		Assert.assertTrue(r.isSuccessful());
	}
	
	//preconditions - need a store object that contains a correct id 
	//postconditions - need to return a single object and test that the result is not null
	private void findStoreTest(){ 
		Assert.assertNotNull(scs.findEntityById(store.getId()));
	}
	
	//preconditions - none
	//postconditions - need to return a List containing store objects, size should be greater than 0 and not null
	private void findAllStoresTest(){ 
		Assert.assertNotNull(scs.getAllEntities());
		Assert.assertTrue(scs.getAllEntities().size()>0);
	}
	
	//preconditions - need a previously created store object and alter one of the attributes 
	//postconditions - need to return a true response and the isOpen value should also be true
	private void updateStoresTest(){ 
		Store toBeUpdated = scs.findEntityById(store.getId());
		toBeUpdated.setOpen(true);
		
		ResponseResult r = scs.updateEntity(toBeUpdated);
		Store updated = scs.findEntityById(toBeUpdated.getId());
		
		Assert.assertTrue(r.isSuccessful());
		Assert.assertEquals(true,updated.isOpen());
		
	}
	
	//preconditions - need a previously created store object
	//postconditions - need to return a true response once successfully deleted
	private void deleteStoresTest(){ 
		Assert.assertTrue(scs.deleteEntity(store));
	}

}

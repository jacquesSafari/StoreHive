package testCases.crudTests;

import java.util.Date;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class StoreOwnerCRUDTest {
	
	private StoreOwnerCrudService socs;
	private Storeowner s;
    
    public StoreOwnerCRUDTest() {
    	socs = new StoreOwnerCrudServiceImpl();
	}
	
    @BeforeClass
    public static void setUpClass(){
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
		s = new Storeowner();
		s.setEmail("tyrondms@gmail.com");
		s.setFullname("Tyrone Adams");
		s.setPassword("123456");
		s.setDeviceId("123456");
		s.setRegisteredDate(new Date());
		
		Assert.assertNotNull(socs.createEntity(s));
	}
	
	private void readStoreOwner(){
	}
	
	private void readAllStoreOwners(){
	}

	private void updateStoreOwner(){
	}
	
	private void deleteStoreOwner(){
	}
}

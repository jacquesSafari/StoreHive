package testCases.crudTests;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.repository.PersistenceManagerExternal;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class StoreOwnerCRUDTest {
	
	private StoreOwnerCrudService socs;
	
	private static Integer id;
    private EntityManager em; 
    public StoreOwnerCRUDTest() {
    	em = PersistenceManagerExternal.INSTANCE.getEntityManager();
    	socs = new StoreOwnerCrudServiceImpl(em);
	}
	
    @BeforeClass
    public static void setUpClass(){
    }
    
    @Test
    public void runTests(){
    	createStoreOwner();
    	readStoreOwner();
    	readAllStoreOwners();
    	deleteStoreOwner();
    }
	
	private void createStoreOwner(){
		Storeowner s = new Storeowner();
		s.setEmail("tyrondmsaaa@gmail.com");
		s.setFullname("Tyrone Adams");
		s.setPassword("123456");
		s.setDeviceId("123456");
		s.setRegisteredDate(new Date());
		Storeowner newOwner = socs.createEntity(s);
		id = newOwner.getId();
		Assert.assertEquals(newOwner.getEmail(),s.getEmail());
	}
	
	private void readStoreOwner(){
		Storeowner found = socs.findByIdNativeQuery(id);
		
		Assert.assertEquals(found.getEmail(),"tyrondmsaaa@gmail.com");
	}
	
	private void readAllStoreOwners(){
		List<Storeowner> allfound = socs.findAll();
		Assert.assertTrue(allfound.size()>0);
	}

	public void updateStoreOwner(){
	}
	
	private void deleteStoreOwner(){
		Storeowner found = socs.findByIdNativeQuery(id);
		socs.delete(found);
		
		Assert.assertTrue(socs.findAll().size()==0);
	}
}

package testCases.crudTests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.domain.Storelocation;
import main.java.com.storehive.application.repository.PersistenceManagerExternal;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;

import org.junit.Assert;
import org.junit.Test;


public class StoreCRUDTest {

	private StoreCrudServices scs;
	private static Integer id;
	private EntityManager em;
	public StoreCRUDTest(){
		em = PersistenceManagerExternal.INSTANCE.getEntityManager();
		scs = new StoreCrudServicesImpl(em);
	}
	
	@Test
	public void runAllTests() {
		createStoreTest();
		findStoreTest();
		findAllStoresTest();
		deleteStoresTest();
	}

	//preconditions - need a store object that contains correct values
	//postconditions - need to return a response result object, with a isSuccessful = true
	private void createStoreTest(){ 
		Storelocation l = new Storelocation();
		l.setLatitude("123.4");
		l.setLongitude("123.0");
		List<Storelocation> all = new ArrayList<Storelocation>();
		Store s = new Store();
		s.setOwnerEmail("tyrondmssoema@gmail.com");
		s.setShopName("Tyrones Kos Winkel");
		s.setDescription("we sell food here");
		s.setStorelocations(all);
		s.setIsOpen("true");
		Store store = scs.createEntity(s);
		id = store.getId();
		Assert.assertEquals(store.getOwnerEmail(),s.getOwnerEmail());
	}
	
	//preconditions - need a store object that contains a correct id 
	//postconditions - need to return a single object and test that the result is not null
	private void findStoreTest(){ 
		Assert.assertNotNull(scs.findById(Store.class,id));
	}
	
	//preconditions - none
	//postconditions - need to return a List containing store objects, size should be greater than 0 and not null
	private void findAllStoresTest(){ 
		Assert.assertTrue(scs.findAll().size()>0);
	}
	
	//preconditions - need a previously created store object
	//postconditions - need to return a true response once successfully deleted
	private void deleteStoresTest(){
		Store toBeDeleted = scs.findById(Store.class, id);
		scs.delete(toBeDeleted);
		
		Assert.assertNull(scs.findById(Store.class, id));
	}

}

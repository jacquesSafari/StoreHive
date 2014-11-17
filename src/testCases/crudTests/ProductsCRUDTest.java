package testCases.crudTests;

import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.domain.Product;
import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.repository.PersistenceManagerExternal;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.ProductCrudServices;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;
import main.java.com.storehive.application.services.crud.impl.ProductCrudServicesImpl;

import org.junit.Assert;
import org.junit.Test;


public class ProductsCRUDTest {

	private ProductCrudServices pcs;
	private CategoryCrudService cs;
	private static Integer id;
	private EntityManager em; 
	
	public ProductsCRUDTest(){
		em = PersistenceManagerExternal.INSTANCE.getEntityManager();
		pcs = new ProductCrudServicesImpl(em);
		cs = new CategoryCrudServiceImpl(em);
	}
	
	@Test
	public void runAllTests() {
		createProductTest();
		findProductTest();
		findAllProductTest();
		updateProductTest();
		deleteProductTest();
	}

	//preconditions - need a product object that contains correct values
	//postconditions - need to return a response result object, with a isSuccessful = true
	private void createProductTest(){ 
		Category c = cs.findById(Category.class, 5);
		
		Product p = new Product();
		p.setCategory(c);
		p.setProductDescription("Mens trousers");
		p.setProductName("Levi Jean");
		p.setProductPrice(250);

		Product created = pcs.createEntity(p);
		id = created.getId();
		
		Assert.assertEquals(p.getProductDescription(),created.getProductDescription());
	}
	
	//preconditions - need a product object that contains a correct id 
	//postconditions - need to return a single object and test that the result is not null
	private void findProductTest(){ 
		Product found = pcs.findById(Product.class, id);
		
		Assert.assertEquals(found.getProductName(),"Levi Jean");
	}
	
	//preconditions - none
	//postconditions - need to return a List containing product objects, size should be greater than 0 and not null
	private void findAllProductTest(){ 
		Assert.assertTrue(pcs.findAll().size()>0);
	}
	
	//preconditions - need a previously created product object and alter one of the attributes 
	//postconditions - need to return a true response and the price value should also be 3000.00
	private void updateProductTest(){ 
		Product toBeUpdated = pcs.findById(Product.class,id);
		toBeUpdated.setProductPrice(3000);
		
		Product updated = pcs.update(toBeUpdated);
		
		Assert.assertEquals(3000,updated.getProductPrice());
		
	}
	
	//preconditions - need a previously created product object
	//postconditions - need to return a true response once successfully deleted
	private void deleteProductTest(){ 
		Product toBeDeleted = pcs.findById(Product.class, id);
		pcs.delete(toBeDeleted);
		Assert.assertNull(pcs.findById(Product.class, id));
	}

}

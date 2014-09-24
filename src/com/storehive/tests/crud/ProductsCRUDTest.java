package com.storehive.tests.crud;

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

import com.storehive.app.domain.Category;
import com.storehive.app.domain.Products;
import com.storehive.app.services.crud.CategoryCrudService;
import com.storehive.app.services.crud.ProductCrudServices;
import com.storehive.app.services.crud.impl.CategoryCrudServiceImpl;
import com.storehive.app.services.crud.impl.ProductCrudServicesImpl;
import com.storehive.app.utilities.ResponseResult;

public class ProductsCRUDTest {

	private ProductCrudServices pcs;
	private Products product;
	private CategoryCrudService cs;
	
	public ProductsCRUDTest() throws UnknownHostException{
		pcs = new ProductCrudServicesImpl();
		cs = new CategoryCrudServiceImpl();
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
		Category c = cs.findCategoryByName("Fashion");
		
		Products p = new Products();
		p.setCategory(c);
		p.setDescription("Mens trousers");
		p.setProductName("Levi Jean");
		p.setPrice("250.00");

		ResponseResult r = pcs.createEntity(p);
		product = pcs.findStoreByProperty("productName", p.getProductName());
		
		Assert.assertTrue(r.isSuccessful());
	}
	
	//preconditions - need a product object that contains a correct id 
	//postconditions - need to return a single object and test that the result is not null
	private void findProductTest(){ 
		Assert.assertNotNull(pcs.findEntityById(product.getId()));
	}
	
	//preconditions - none
	//postconditions - need to return a List containing product objects, size should be greater than 0 and not null
	private void findAllProductTest(){ 
		Assert.assertNotNull(pcs.getAllEntities());
		Assert.assertTrue(pcs.getAllEntities().size()>0);
	}
	
	//preconditions - need a previously created product object and alter one of the attributes 
	//postconditions - need to return a true response and the price value should also be 3000.00
	private void updateProductTest(){ 
		Products toBeUpdated = pcs.findEntityById(product.getId());
		toBeUpdated.setPrice("3000.00");
		
		ResponseResult r = pcs.updateEntity(toBeUpdated);
		Products updated = pcs.findEntityById(toBeUpdated.getId());
		
		Assert.assertTrue(r.isSuccessful());
		Assert.assertEquals("3000.00",updated.getPrice());
		
	}
	
	//preconditions - need a previously created product object
	//postconditions - need to return a true response once successfully deleted
	private void deleteProductTest(){ 
		Assert.assertTrue(pcs.deleteEntity(product));
	}

}

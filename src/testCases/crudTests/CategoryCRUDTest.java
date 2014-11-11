package testCases.crudTests;

import java.net.UnknownHostException;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.junit.Assert;
import org.junit.Test;

public class CategoryCRUDTest {

	private CategoryCrudService cs;
	private Category found;
	public CategoryCRUDTest() throws UnknownHostException{
		cs = new CategoryCrudServiceImpl();
	}

	@Test
	public void runTests(){
		createCategoryTestPass();
		createCategoryHandleFail();
		findNewCategory();
		findAllCategory();
		updateCategory();
		deleteCategory();
	}
	
	private void createCategoryTestPass(){
		Category a = new Category();
		a.setCategoryName("Fruit & Veg");
		a.setCategoryDescription("fruit for sale");
		
		ResponseResult output = cs.createEntity(a);
		found = cs.findCategoryByName(a.getCategoryName());
		
		Assert.assertEquals(true, output.isSuccessful());
	}
	
	private void createCategoryHandleFail(){
		Category a = new Category();
		a.setCategoryName("Fruit & Veg");
		a.setCategoryDescription("fruit for sale");
		
		ResponseResult output = cs.createEntity(a);
		Assert.assertEquals(false, output.isSuccessful());
	}
	
	private void findNewCategory(){
		Category toBeFound = cs.findEntityById(found.getId());
		Assert.assertEquals(found.getCategoryName(), toBeFound.getCategoryName());
	}
	
	private void findAllCategory(){
		Assert.assertTrue(cs.getAllEntities().size()>0);
	}
	
	private void updateCategory(){
		Category toBeUpdated = found;
		toBeUpdated.setCategoryDescription("Selling only apples now");
		
		cs.updateEntity(toBeUpdated);
		Category updated = cs.findEntityById(toBeUpdated.getId());
		Assert.assertEquals("Selling only apples now",updated.getCategoryDescription());
	}
	
	private void deleteCategory(){
		Category toBeDeleted = found;
		cs.deleteEntity(toBeDeleted);
		Assert.assertNull(cs.findEntityById(toBeDeleted.getId()));
	}

}

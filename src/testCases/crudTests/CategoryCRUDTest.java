package testCases.crudTests;


import javax.persistence.EntityManager;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.repository.PersistenceManagerExternal;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;

import org.junit.Assert;
import org.junit.Test;

public class CategoryCRUDTest {

	private CategoryCrudService cs;
	
	private static Integer id;
    private EntityManager em; 
	public CategoryCRUDTest(){
		em = PersistenceManagerExternal.INSTANCE.getEntityManager();
		cs = new CategoryCrudServiceImpl(em);
	}

	@Test
    public void runTests(){
		createCategoryTestPass();
		findAllCategory();
    	deleteCategory();
    }
	
	private void createCategoryTestPass(){
		Category a = new Category();
		a.setCategoryName("Fruit & Veg");
		a.setCategoryDescription("fruit for sale");

		Category found = cs.createEntity(a);
		id = found.getId();
		Assert.assertEquals(a.getCategoryDescription(),found.getCategoryDescription());
	}
	
	private void findAllCategory(){
		Assert.assertTrue(cs.findAll().size()>0);
	}
	
	private void deleteCategory(){
		Category toBeDeleted = cs.findSingleItemByQuery(""+id);
		cs.delete(toBeDeleted);
		Assert.assertNull(cs.findById(Category.class, id));
	}

}

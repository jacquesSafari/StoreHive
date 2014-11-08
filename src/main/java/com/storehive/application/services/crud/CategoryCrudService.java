package main.java.com.storehive.application.services.crud;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.repository.MongoResporitoryService;

public interface CategoryCrudService extends MongoResporitoryService<Category> {
	
	public boolean doesCategoryExist(String categoryName);
	
	public Category findCategoryByName(String categoryName);
	
	public int countCategoriesAvailable();
}

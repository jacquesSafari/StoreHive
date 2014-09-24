package com.storehive.app.services.crud;

import com.storehive.app.domain.Category;
import com.storehive.app.repository.MongoResporitoryService;

public interface CategoryCrudService extends MongoResporitoryService<Category> {
	
	public boolean doesCategoryExist(String categoryName);
	
	public Category findCategoryByName(String categoryName);
	
	public int countCategoriesAvailable();
}

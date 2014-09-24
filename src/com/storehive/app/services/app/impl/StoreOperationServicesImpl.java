package com.storehive.app.services.app.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.storehive.app.domain.Category;
import com.storehive.app.services.app.StoreOperationServices;
import com.storehive.app.services.crud.CategoryCrudService;
import com.storehive.app.services.crud.impl.CategoryCrudServiceImpl;

public class StoreOperationServicesImpl implements StoreOperationServices {

	private CategoryCrudService cs;
	
	public StoreOperationServicesImpl() throws UnknownHostException{
		cs = new CategoryCrudServiceImpl();
	}
	@Override
	public List<Category> getCategoriesAvailable() {
		List<Category> allCategories = new ArrayList<Category>();
		
		if(cs.countCategoriesAvailable()>0){
			allCategories.addAll(cs.getAllEntities());
		}
		
		return allCategories;
	}

}

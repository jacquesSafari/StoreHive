package com.storehive.tests.crud;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.services.crud.impl.StoreOwnerCrudServiceImpl;

public class FindStoreOwnerTestCase {

	private static StoreOwnerCrudService crudService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		crudService = new StoreOwnerCrudServiceImpl();
	}
	
	@Test
	public void runTests(){
		findStoreOwnerPass();
		findStoreOwnerHandleFail();
	}
	
	private void findStoreOwnerPass() {
		Assert.assertNotNull(crudService.findStoreOwnerByUsername("tyrone"));
	}

	private void findStoreOwnerHandleFail() {
		Assert.assertNull(crudService.findStoreOwnerByUsername("nicoles"));
	}
}

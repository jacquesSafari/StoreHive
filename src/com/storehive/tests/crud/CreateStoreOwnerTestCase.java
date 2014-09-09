package com.storehive.tests.crud;

import java.util.HashMap;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.services.crud.impl.StoreOwnerCrudServiceImpl;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class CreateStoreOwnerTestCase {
	
	private static StoreOwnerCrudService crudService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		crudService = new StoreOwnerCrudServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public void createStoreOwnerTestFail(){
		
	}
	
	@Test
	public void createStoreOwnerTestPass(){
		HashMap<String, String> details = new HashMap<String, String>();
		details.put("name", "Tyrone");
		details.put("surname", "Adams");
		details.put("username", "labamba");
		details.put("password", "1234568789");
		details.put("reagistrationDate",new DateTime().toString());
		
		StoreOwner a = ApplicationFactory.buildStoreOwner(details);
		
		Assert.assertTrue("labamba".equals(crudService.createEntity(a)));
	}
}

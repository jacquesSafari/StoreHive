package com.storehive.tests.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

public class ApiTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void registerUserViaApi() throws IOException {
		URL url = new URL("http://10.0.0.6/business/newStoreOwner");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		
	
	}

}

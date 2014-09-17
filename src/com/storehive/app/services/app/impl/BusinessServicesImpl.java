package com.storehive.app.services.app.impl;

import java.net.UnknownHostException;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

import com.mongodb.BasicDBObject;
import com.storehive.app.domain.StoreOwner;
import com.storehive.app.services.app.BusinessServices;
import com.storehive.app.services.crud.StoreOwnerCrudService;
import com.storehive.app.services.crud.impl.StoreOwnerCrudServiceImpl;
import com.storehive.app.utilities.ErrorCodes;
import com.storehive.app.utilities.ResponseResult;
import com.storehive.app.utilities.factory.ApplicationFactory;

public class BusinessServicesImpl implements BusinessServices{

	private StoreOwnerCrudService stcs;
	
	public BusinessServicesImpl() throws UnknownHostException {
		stcs = new StoreOwnerCrudServiceImpl();
	}
	@Override
	public ResponseResult registerClient(JSONObject newClient) {
		BasicDBObject clientDetails = new BasicDBObject();
		clientDetails.put("username", newClient.get("username").toString());
		clientDetails.put("name", newClient.get("name").toString());
		clientDetails.put("surname", newClient.get("surname").toString());
		clientDetails.put("registrationDate", new DateTime().toString());
		clientDetails.put("deviceId", newClient.get("deviceId").toString());
		clientDetails.put("password",newClient.get("password").toString());
		
		StoreOwner a = ApplicationFactory.buildStoreOwner(clientDetails);
		return stcs.createEntity(a);
	}
	
	@Override
	public ResponseResult loginClient(JSONObject clientToLogin) {
		ResponseResult loginOutput = new ResponseResult();
		StoreOwner ownerExists = stcs.findStoreOwnerByUsername(clientToLogin.get("username").toString());
		
		if(ownerExists==null){
			loginOutput.setSuccessful(false);
			loginOutput.setErrorCode(ErrorCodes.LOG_ERR_1);
			loginOutput.setErrorMessage("The username provided does not exist.");
		}else{
			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			String userPass = clientToLogin.get("password").toString();
			
			if(encryptor.checkPassword(userPass, ownerExists.getPassword())){
				loginOutput.setSuccessful(true);
			}else{
				loginOutput.setSuccessful(false);
				loginOutput.setErrorCode(ErrorCodes.LOG_ERR_2);
				loginOutput.setErrorMessage("The password provided is incorrect.");
			}
		}
		return loginOutput;
	}

	
}


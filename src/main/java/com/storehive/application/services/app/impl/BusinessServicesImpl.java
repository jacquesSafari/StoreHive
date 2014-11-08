package main.java.com.storehive.application.services.app.impl;

import java.net.UnknownHostException;

import main.java.com.storehive.application.domain.StoreOwner;
import main.java.com.storehive.application.services.app.BusinessServices;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;
import main.java.com.storehive.application.utilities.factory.ApplicationFactory;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

import com.mongodb.BasicDBObject;

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


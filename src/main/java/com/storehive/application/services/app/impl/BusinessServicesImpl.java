package main.java.com.storehive.application.services.app.impl;


public class BusinessServicesImpl{

//	private StoreOwnerCrudService stcs;
//	
//	public BusinessServicesImpl(){
//		stcs = new StoreOwnerCrudServiceImpl();
//	}
//	@Override
//	public ResponseResult registerClient(JSONObject newClient) {
//		BasicDBObject clientDetails = new BasicDBObject();
//		clientDetails.put("fullname", newClient.get("fullname").toString());
//		clientDetails.put("email", newClient.get("email").toString());
//		clientDetails.put("registrationDate", new DateTime().toString());
//		clientDetails.put("deviceId", newClient.get("deviceId").toString());
//		clientDetails.put("password",newClient.get("password").toString());
//		
//		StoreOwner a = ApplicationFactory.buildStoreOwner(clientDetails);
//		return stcs.createEntity(a);
//	}
//	
//	@Override
//	public ResponseResult loginClient(JSONObject clientToLogin) {
//		ResponseResult loginOutput = new ResponseResult();
//		StoreOwner ownerExists = stcs.findStoreOwnerByEmail(clientToLogin.get("email").toString());
//		
//		if(ownerExists==null){
//			loginOutput.setSuccessful(false);
//			loginOutput.setErrorCode(ErrorCodes.LOG_ERR_1);
//			loginOutput.setErrorMessage("The username provided does not exist.");
//		}else{
//			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
//			String userPass = clientToLogin.get("password").toString();
//			
//			if(encryptor.checkPassword(userPass, ownerExists.getPassword())){
//				loginOutput.setSuccessful(true);
//			}else{
//				loginOutput.setSuccessful(false);
//				loginOutput.setErrorCode(ErrorCodes.LOG_ERR_2);
//				loginOutput.setErrorMessage("The password provided is incorrect.");
//			}
//		}
//		return loginOutput;
//	}
	
}


package main.java.com.storehive.application.services.app.impl;

import org.json.simple.JSONObject;

import main.java.com.storehive.application.domain.Store;
import main.java.com.storehive.application.services.app.StoreOperationServices;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;


public class StoreOperationServicesImpl implements StoreOperationServices{

	StoreCrudServices scs;
	
	public StoreOperationServicesImpl(){
		scs = new StoreCrudServicesImpl();
	}
	
	@Override
	public ResponseResult registerStore(Store s) {
		ResponseResult r = new ResponseResult();
		try{
			Store created = scs.createEntity(s);
		
			r.setLink("/api/storeServices/viewStoreDetails/"+created.getId());
			r.setErrorMessage(""+created.getId()+"");
			r.setSuccessful(true);
		}catch(Exception e){
			r.setErrorCode(ErrorCodes.STORE_ERR_1);
			r.setErrorMessage("There was an error creating the store");
			r.setSuccessful(false);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public JSONObject viewStoreDetails(Integer id) {
		JSONObject j = new JSONObject();
		Store s = scs.findById(Store.class, id);
		
		if(s!=null){
			j.put("shopName", s.getShopName());
			j.put("description", s.getDescription());
		}
		return j;
	}

	@Override
	public JSONObject openStore(Store s) {
		JSONObject j = new JSONObject();
		
		Store updated = scs.update(s);
		
		if(updated!=null){
			j.put("isSuccessfull", true);
		}else{
			j.put("isSuccessfull", false);
		}
		return j;
	}

	@Override
	public JSONObject closeStore(Store s) {
		JSONObject j = new JSONObject();
		
		Store updated = scs.update(s);
		
		if(updated!=null){
			j.put("isSuccessfull", true);
		}else{
			j.put("isSuccessfull", false);
		}
		return j;
	}

}

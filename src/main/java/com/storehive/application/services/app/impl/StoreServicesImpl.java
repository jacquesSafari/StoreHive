package main.java.com.storehive.application.services.app.impl;

import java.util.Date;

import javax.persistence.NoResultException;

import main.java.com.storehive.application.domain.Storeowner;
import main.java.com.storehive.application.services.app.StoreServices;
import main.java.com.storehive.application.services.crud.StoreCrudServices;
import main.java.com.storehive.application.services.crud.StoreLocationCrudServices;
import main.java.com.storehive.application.services.crud.StoreOwnerCrudService;
import main.java.com.storehive.application.services.crud.impl.StoreCrudServicesImpl;
import main.java.com.storehive.application.services.crud.impl.StoreLocationServicesImpl;
import main.java.com.storehive.application.services.crud.impl.StoreOwnerCrudServiceImpl;
import main.java.com.storehive.application.utilities.ErrorCodes;
import main.java.com.storehive.application.utilities.ResponseResult;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class StoreServicesImpl implements StoreServices {

	StoreOwnerCrudService scs;
	StoreCrudServices ss;
	StoreLocationCrudServices slcs;
	
	public StoreServicesImpl(){
		scs = new StoreOwnerCrudServiceImpl();
		ss = new StoreCrudServicesImpl();
		slcs = new StoreLocationServicesImpl();
	}
	
	@Override
	public ResponseResult registerNewClient(JSONObject s) {
		ResponseResult r = new ResponseResult();
		StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
		
		Storeowner so = new Storeowner();
		so.setDeviceId(s.get("deviceId").toString());
		so.setEmail(s.get("email").toString());
		so.setFullname(s.get("fullname").toString());
		so.setRegisteredDate(new Date());
		
		String clearTextPassword = s.get("password").toString();
		so.setPassword(spe.encryptPassword(clearTextPassword));
		try{
			//do a check with raw query
			if(scs.findByQuery(so.getEmail())==null){
				Storeowner created = scs.createEntity(so);
				r.setErrorMessage(""+so.getId()+"");
				r.setErrorCode(ErrorCodes.REG_SUC_1);
				r.setLink("/storeOwnerServices/viewStoreOwnerDetails/"+created.getId());
				r.setSuccessful(true);
			}else{
				r.setErrorCode(ErrorCodes.REG_ERR_2);
				r.setErrorMessage("User already exists with the email address provided");
				r.setSuccessful(false);
			}
		}catch(Exception e){
			r.setErrorCode(ErrorCodes.REG_ERR_2);
			r.setErrorMessage("Something went wrong while registering the user");
			r.setSuccessful(false);
		}
		return r;
	}

	@Override
	public JSONArray getAllStoreOwners() {
		JSONArray a = new JSONArray();
		
		for(Storeowner s:scs.findAll()){
			JSONObject j = new JSONObject();
			j.put("id", s.getId());
			j.put("fullname", s.getFullname());
			j.put("deviceId", s.getDeviceId());
			j.put("email", s.getEmail());
			j.put("registeredDate", s.getRegisteredDate().toString());
			j.put("link","/storeOwnerServices/viewStoreOwnerDetails/"+s.getId());
			a.add(j);
		}
		return a;
	}

	@Override
	public JSONObject viewUserProfile(Integer id) {
		JSONObject j = new JSONObject();
		if(scs.findById(Storeowner.class, id)!=null){
			Storeowner s = scs.findById(Storeowner.class, id);
			j.put("email", s.getEmail());
			j.put("fullname", s.getFullname());
			j.put("registeredDate", s.getRegisteredDate().toString());
		}
		return j;
	}

	@Override
	public JSONObject updateUserProfile(Storeowner s) {
		Storeowner updated = scs.update(s);
		JSONObject j = new JSONObject();
		j.put("email", updated.getEmail());
		j.put("fullname", updated.getFullname());
		return j;
	}

	@Override
	public ResponseResult deleteStoreOwnerProfile(Integer id) {
		ResponseResult r = new ResponseResult();
		Storeowner toDelete = scs.findById(Storeowner.class, id);
		
		if(toDelete!=null){
			ss.deleteByQueryWithId(id);
			scs.delete(toDelete);
			r.setSuccessful(true);
		}else{
			r.setErrorCode(ErrorCodes.OWNER_ERR1_DELETE);
			r.setErrorMessage("There was an error trying to remove the profile");
			r.setSuccessful(false);
		}
		return r;
	}

	@Override
	public ResponseResult loginUser(String email, String password) {

		ResponseResult r = new ResponseResult();
		try{
			Storeowner s = scs.findSingleItemByQuery(email);
			if(s!=null){
				StrongPasswordEncryptor sp = new StrongPasswordEncryptor();
				if(sp.checkPassword(password, s.getPassword())){
					r.setSuccessful(true);
					r.setErrorCode(ErrorCodes.LOG_SUC_1);
					r.setLink("/storeOwnerServices/viewStoreOwnerDetails/"+s.getId());
					r.setErrorMessage(""+s.getId()+"");
				}else{
					r.setSuccessful(false);
					r.setErrorCode(ErrorCodes.LOG_ERR_2);
					r.setErrorMessage("The password provided is incorrect");
				}
				
			}
		}catch(NoResultException nre){
			r.setSuccessful(false);
			r.setErrorCode(ErrorCodes.LOG_ERR_1);
			r.setErrorMessage("The email provided does not exist");
		}
		return r;
	}

}

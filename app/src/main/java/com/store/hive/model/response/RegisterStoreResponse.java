package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tinashe.
 */
public class RegisterStoreResponse extends JSONModel{

    @SerializedName("storeId")
    private int storeId;

    @SerializedName("isSuccessful")
    private boolean isSuccessful;


    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public RegisterStoreResponse(JSONObject jsonObject){
        try{
            this.isSuccessful = jsonObject.getBoolean("isSuccessful");
            this.storeId = jsonObject.getInt("storeId");
        }catch (JSONException ex){
            System.out.println("Constructor:\n" + ex.getMessage());
        }

    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    @Override
    public String toString() {
        return "RegisterStoreResponse{" +
                "storeId=" + storeId +
                "isSuccessful=" + isSuccessful() +
                '}';
    }
}

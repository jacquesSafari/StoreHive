package com.store.hive.model.people;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

/**
 * Created by tinashe
 */
public class StoreOwner extends JSONModel {

    @SerializedName("ownerId")
    private int ownerID;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("email")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("deviceId")
    private String deviceID;

    public StoreOwner(){

    }

    public StoreOwner(String fullName, String userName, String password, String deviceID) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.deviceID = deviceID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}

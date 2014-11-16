package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinashe.
 */
public class RegisterOwnerResponse extends BaseResponse {

    @SerializedName("ownerId")
    private int ownerID;

    public RegisterOwnerResponse(){
        super();
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public String toString() {
        return "RegisterOwnerResponse{" +
                "ownerID=" + ownerID +
                "isSuccessful=" + isSuccessful() +
                "link=" + getLink() +
                '}';
    }
}

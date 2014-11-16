package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinashe.
 */
public class RegisterStoreResponse extends BaseResponse{

    @SerializedName("storeId")
    private int storeId;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}

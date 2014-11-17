package com.store.hive.model.request;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

/**
 * Created by tinashe.
 */
public class RegisterStoreRequest extends JSONModel{

    @SerializedName("shopName")
    private String shopName;

    @SerializedName("description")
    private String description;

    @SerializedName("ownerId")
    private int ownerId;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}

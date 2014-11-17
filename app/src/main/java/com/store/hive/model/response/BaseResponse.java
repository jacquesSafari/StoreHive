package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;
import com.store.hive.model.JSONModel;

/**
 * Created by tinashe
 */
public class BaseResponse extends JSONModel{

    @SerializedName("isSuccessful")
    private boolean isSuccessful;

    @SerializedName("link")
    private String link;

    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("errorCode")
    private String errorCode;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "isSuccessful=" + isSuccessful +
                ", link='" + link + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}

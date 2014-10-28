package com.store.hive.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinashe
 */
public class ResponseResult {

    @SerializedName("isSuccessful")
    private boolean isSuccessful;

    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("errorCode")
    private String errorCode;

    public ResponseResult(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public ResponseResult(boolean isSuccessful, String errorMessage) {
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
    }

    public ResponseResult(boolean isSuccessful, String errorMessage, String errorCode) {
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }


}

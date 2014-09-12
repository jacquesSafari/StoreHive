package com.store.hive.model.response;

/**
 * Created by tinashe
 */
public class ResponseResult {

    private boolean isSuccesfull;
    private String errorMessage;

    public ResponseResult(boolean isSuccesfull) {
        this.isSuccesfull = isSuccesfull;
    }

    public ResponseResult(boolean isSuccesfull, String errorMessage) {
        this.isSuccesfull = isSuccesfull;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccesfull() {
        return isSuccesfull;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

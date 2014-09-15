package com.store.hive.service;

import com.store.hive.model.response.ResponseResult;

/**
 * Created by tinashe
 */
public interface OnRequestCompleteLister {
    public abstract void onRequestComplete(ResponseResult response);
    public abstract void onRequestError();
}

package com.store.hive.service;

import com.store.hive.model.response.BaseResponse;

/**
 * Created by tinashe
 */
public interface OnRequestCompleteLister {
    public abstract void onRequestComplete(BaseResponse response);
    public abstract void onRequestError();
}

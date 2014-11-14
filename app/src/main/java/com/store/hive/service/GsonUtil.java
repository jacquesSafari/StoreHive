package com.store.hive.service;

import com.google.gson.Gson;

/**
 * Created by tinashe
 */
public class GsonUtil {

    private static Gson gson;

    public synchronized static Gson getInstance(){

        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}

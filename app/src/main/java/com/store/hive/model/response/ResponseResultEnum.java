package com.store.hive.model.response;

/**
 * Created by tinashe
 */
public enum ResponseResultEnum {

    isSuccessful{
        @Override
        public String getKey(){
            return "isSuccessfull";
        }
    },
    errorMessage{
        @Override
        public String getKey(){
            return "errorMessage";
        }
    },
    errorCode{
        @Override
        public String getKey(){
            return "errorCode";
        }
    };

    abstract public String getKey();
}

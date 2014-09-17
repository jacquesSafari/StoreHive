package com.storehive.app.utilities;

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

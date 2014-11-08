package main.java.com.storehive.application.utilities;

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

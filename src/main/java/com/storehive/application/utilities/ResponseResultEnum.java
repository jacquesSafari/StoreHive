package main.java.com.storehive.application.utilities;

public enum ResponseResultEnum {

    isSuccessful{
        @Override
        public String getKey(){
            return "isSuccessfull";
        }
    },
    statusMessage{
        @Override
        public String getKey(){
            return "statusMessage";
        }
    },
    statusCode{
        @Override
        public String getKey(){
            return "statusCode";
        }
    },
    link{
    	@Override
        public String getKey(){
            return "link";
        }
    }
    ;

    abstract public String getKey();
}

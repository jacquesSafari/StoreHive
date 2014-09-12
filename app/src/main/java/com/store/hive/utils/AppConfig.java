package com.store.hive.utils;

/**
 * Created by tinashe
 */
public class AppConfig {
    private static AppConfig ourInstance = new AppConfig();

    public static AppConfig getInstance() {
        return ourInstance;
    }

    private AppConfig() {
    }

    public static boolean isMethodPossible(int for_api){

        if (android.os.Build.VERSION.SDK_INT >= for_api){
            return true;
        } else {
            return false;
        }
    }
}

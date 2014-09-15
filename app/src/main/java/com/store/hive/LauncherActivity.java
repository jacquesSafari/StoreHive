package com.store.hive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.store.hive.model.people.RegisteredUser;
import com.store.hive.utils.AppConfig;

/**
 * Created by tinashe
 */
public class LauncherActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        RegisteredUser user = AppConfig.getRegisteredUser(this);
        if(user.isLoggedIn()){
            startActivity(new Intent(this, com.store.hive.store_owner.MainActivity.class));
        } else {
            startActivity(new Intent(this, OnBoardingActivity.class));
        }

        finish();
        /*
        Hackish way to start an AppCompat activity without showing the action bar
         */
    }
}

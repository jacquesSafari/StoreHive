package com.store.hive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by tinashe
 */
public class LauncherActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        startActivity(new Intent(this, OnBoardingActivity.class));
        finish();
        /*
        Hackish way to start an AppCompat activity without showing the action bar
         */
    }
}

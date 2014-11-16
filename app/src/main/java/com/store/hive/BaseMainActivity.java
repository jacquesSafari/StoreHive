package com.store.hive;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by tinashe
 */
public abstract class BaseMainActivity extends ActionBarActivity {
    protected abstract int getLayoutResource();

    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(getLayoutResource());

        toolbar = (Toolbar) findViewById(R.id.app_action_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    public Toolbar getToolbar(){
        return toolbar;
    }


}

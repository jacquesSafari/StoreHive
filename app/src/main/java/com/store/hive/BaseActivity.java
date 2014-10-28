package com.store.hive;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by tinashe.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected abstract int getLayoutResource();

    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(getLayoutResource());

        toolbar = (Toolbar) findViewById(R.id.app_action_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
           // toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            //  toolbar.setTitleTextColor(Color.WHITE);
        }
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    protected Toolbar getToolbar(){
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}

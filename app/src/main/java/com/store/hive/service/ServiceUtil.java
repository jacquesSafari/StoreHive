package com.store.hive.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.store.hive.R;

/**
 * Created by tinashe
 */
public class ServiceUtil {

    public static boolean isConnected(Activity activity){
        ConnectivityManager cm =
                (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return  activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void showNoConnectionDialog(Activity activity){
        new AlertDialog.Builder(activity)
                .setMessage(activity.getString(R.string.sh_error_no_network))
                .setPositiveButton(activity.getString(R.string.sh_ok), null)
                .show();
    }
}

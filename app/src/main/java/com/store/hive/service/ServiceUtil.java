package com.store.hive.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.store.hive.R;
import com.store.hive.custom.AppAlertDialog;

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

        AppAlertDialog dialog = new AppAlertDialog.Builder(activity)
                .setTitle(R.string.sh_no_connectivity)
                .setIcon(R.drawable.ic_action_info)
                .setMessage(R.string.sh_error_no_network)
                .setPositiveButton(R.string.sh_ok, null)
                .build();

        dialog.show();
    }
}

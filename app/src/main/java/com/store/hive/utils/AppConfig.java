package com.store.hive.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.store.hive.OnBoardingActivity;
import com.store.hive.R;
import com.store.hive.model.people.RegisteredUser;

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
        return  (android.os.Build.VERSION.SDK_INT >= for_api);
    }

    public static void saveAuthState(Context context, RegisteredUser user){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.sh_pref_is_logged_in), user.isLoggedIn());
        editor.putString(context.getString(R.string.sh_pref_full_name), user.getFullName());
        editor.putString("user_email", user.getEmail());
        editor.putInt("user_id", user.getOwnerId());
        editor.apply();
    }

    public static RegisteredUser getRegisteredUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new RegisteredUser(preferences.getBoolean(context.getString(R.string.sh_pref_is_logged_in), false),
                preferences.getInt("user_id", 0),
                preferences.getString(context.getString(R.string.sh_pref_full_name), ""),
                preferences.getString("user_email", ""));
    }

    public static void logoutUser(Activity activity){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(activity.getString(R.string.sh_pref_is_logged_in), false);
        editor.apply();

        Intent intent = new Intent(activity, OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}

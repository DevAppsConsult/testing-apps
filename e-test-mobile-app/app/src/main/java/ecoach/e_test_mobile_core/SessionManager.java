package ecoach.e_test_mobile_core;

/**
 * Created by banktech on 12/1/2014.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import ecoach.e_test_mobile_application.Logout;
import ecoach.e_test_mobile_application.SplashActivity;

public class SessionManager {
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "pass";
    // Shared Preference file name
    private static final String PREF_NAME = "Pref";
    // Shared Preferences Key
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref;
    Editor editor;
    // an editor is used to edit your preferences
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        /*
         * Setting the mode as Private so that the preferences should only be
         * used in this application and not by any other application
         * also the preferences can be Shared Globally by using -
         *Activity.MODE_WORLD_READABLE - to read Application components data
         *globally and,
         *Activity.MODE_WORLD_WRITEABLE -file can be written globally by any
         *other application.
         */


        pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        /*
         * the same pref mode can be set to private by using 0 as a flag instead
         * of Acticity.MODE_PRIVATE
         */
        editor = pref.edit();
    }

    // Creating a login session
    public void createLoginSession(String user, String pass) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER, user);
        editor.putString(KEY_PASSWORD, pass);
        editor.commit();
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect to Login Activity
            Intent i = new Intent(context, SplashActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
        }

    }

    // Getting stored session data of user and returing this data as a HASH MAP
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // username
        user.put(KEY_USER, pref.getString(KEY_USER, null));

        // user password id
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        return user;
    }

    // Clearing a session data
    public void logoutUser() {
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, Logout.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}

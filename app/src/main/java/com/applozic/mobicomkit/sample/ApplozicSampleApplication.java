package com.applozic.mobicomkit.sample;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import Models.MyUser;
import io.fabric.sdk.android.Fabric;

/**
 * Created by sunil on 21/3/16.
 */
public class ApplozicSampleApplication extends MultiDexApplication {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fabric.with(this, new Crashlytics());

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        //Register Parse subclasses
        ParseUser.registerSubclass(MyUser.class);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("7GWTAV0VKJZc3re8axRjmcPf81nYytJXAAzIVaEH")
                .clientKey("vQUXFlGRAu7bVF5ptaMYWNXglH7bA5iScKhnWLCv")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        ParseFacebookUtils.initialize(getApplicationContext());

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

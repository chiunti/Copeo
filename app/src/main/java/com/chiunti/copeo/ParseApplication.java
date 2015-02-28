package com.chiunti.copeo;

/**
 * Created by chiunti on 26/02/15.
 */
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        //Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8h9rGBxp2yelNGDjr6fiemfVoP1HcbRIV3Zer81u", "B2oDwKIJsAL42kuUURK0v2qURpa2EmTPh3qKHDnU");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
package com.sathvi.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("44SBPtaXOTmgweQTopUtN0h6sToDXGjzH3PCt3gy")
                .clientKey("hSIV2xdMbuEYMKZunnF5bx9YDOUc6L3WyOcdMQkl")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    public static class MainActivity {
    }
}

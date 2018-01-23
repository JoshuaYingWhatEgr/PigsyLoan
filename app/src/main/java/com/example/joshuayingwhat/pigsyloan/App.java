package com.example.joshuayingwhat.pigsyloan;

import android.app.Application;

/**
 * Created by JoshuaYingWhat on 2017/7/7.
 */
public class App extends Application {
    public static App INSTANCE;

    public static App getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}

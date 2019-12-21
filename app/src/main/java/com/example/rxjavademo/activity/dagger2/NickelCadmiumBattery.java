package com.example.rxjavademo.activity.dagger2;

import android.util.Log;

import javax.inject.Inject;

public class NickelCadmiumBattery implements Battery {
    private static final String TAG = "SmartPhone";
    @Inject
    public NickelCadmiumBattery() {
    }

    @Override
    public void showType() {
        Log.i(TAG, "This is a Nickel Cadmium Battery");
    }
}

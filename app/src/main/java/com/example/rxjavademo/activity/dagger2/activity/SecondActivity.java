package com.example.rxjavademo.activity.dagger2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.dagger2.App;
import com.example.rxjavademo.activity.dagger2.SmartPhone;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {
    @Inject
    SmartPhone smartPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        App.getApp().getSmartPhoneComponent().inject(this);
        smartPhone.makeACall();
    }
}

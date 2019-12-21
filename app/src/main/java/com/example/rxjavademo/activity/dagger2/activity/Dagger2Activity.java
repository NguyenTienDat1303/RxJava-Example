package com.example.rxjavademo.activity.dagger2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.dagger2.App;
import com.example.rxjavademo.activity.dagger2.SmartPhone;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {
    @Inject
    SmartPhone smartPhone;

    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        btnClick = findViewById(R.id.btnClick);
//        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();
//        smartPhone = smartPhoneComponent.getSmartPhone();
        App.getApp().getSmartPhoneComponent().inject(this);
        smartPhone.makeACall();
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dagger2Activity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}

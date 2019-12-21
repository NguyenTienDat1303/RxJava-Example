package com.example.rxjavademo.activity.dagger2;

import com.example.rxjavademo.activity.dagger2.activity.Dagger2Activity;
import com.example.rxjavademo.activity.dagger2.activity.SecondActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MemoryCardModul.class, NCBatteryModule.class})
public interface SmartPhoneComponent {
//    SmartPhone getSmartPhone();
    void inject(Dagger2Activity mainActivity);
    void inject(SecondActivity mainActivity);
}

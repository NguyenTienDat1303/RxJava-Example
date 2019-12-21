package com.example.rxjavademo.activity.room.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ContactAppComponent {

}

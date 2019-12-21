package com.example.rxjavademo.activity.room.di;

import android.app.Application;

import androidx.room.Room;

import com.example.rxjavademo.activity.room.ContactAppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    @Singleton
    ContactAppDataBase provideContactAppDataBase(Application application){
        return Room.databaseBuilder(application, ContactAppDataBase.class, "ContactDB").build();
    }
}

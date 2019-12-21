package com.example.rxjavademo.activity.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rxjavademo.activity.room.db.entity.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactAppDataBase extends RoomDatabase {
    public abstract ContactDAO getContractDAO();
}

package com.example.rxjavademo.activity.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rxjavademo.activity.room.db.entity.Contact;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ContactDAO {
    @Insert
    public long addContact(Contact contract);

    @Update
    public void updateContact(Contact contract);

    @Delete
    public void deleteContact(Contact contract);

    @Query("select * from contacts")
    Flowable<List<Contact>> getContacts();

    @Query("select * from contacts where contact_id == :contactId")
    public Contact getContact(long contactId);
}

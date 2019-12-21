package com.example.rxjavademo.activity.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rxjavademo.activity.room.db.entity.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRespository contactRespository;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRespository = new ContactRespository(application);
    }

    public LiveData<List<Contact>> getAllContacs(){
        return contactRespository.getListMutableLiveData();
    }

    public void create(String name, String email){
        contactRespository.createContact(name, email);
    }

    public void update(Contact contact){
        contactRespository.updateContact(contact);
    }

    public void delete(Contact contact){
        contactRespository.deleteContact(contact);
    }

    public void clear(){
        contactRespository.clear();
    }
}

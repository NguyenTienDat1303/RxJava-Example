package com.example.rxjavademo.activity.room;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.rxjavademo.activity.room.db.entity.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactRespository {
    private Application mApplication;
    private ContactAppDataBase mContactAppDataBase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<Contact>> listMutableLiveData = new MutableLiveData<>();
    private long rowIdInserted = -1;

    public ContactRespository(Application application) {
        this.mApplication = application;


        compositeDisposable.add(
                mContactAppDataBase.getContractDAO().getContacts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Contact>>() {
                            @Override
                            public void accept(List<Contact> contacts) throws Exception {
                                Log.i("contactsAppDatabase", "" + contacts.size());
                                listMutableLiveData.postValue(contacts);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i("contactsAppDatabase", "" + throwable.getCause());
                            }
                        })
        );
    }

    public void deleteContact(final Contact contact) {
        compositeDisposable.add(
                Completable
                        .fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mContactAppDataBase.getContractDAO().deleteContact(contact);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(mApplication, "Contact delete successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(mApplication, "Contact delete error", Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    public void updateContact(final Contact contact) {
        Log.i("updateContact", ""+contact.getId());
        Log.i("updateContact", contact.getName());
        Log.i("updateContact", contact.getEmail());
        compositeDisposable.add(
                Completable
                        .fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mContactAppDataBase.getContractDAO().updateContact(contact);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(mApplication, "Contact update successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(mApplication, "Contact update error", Toast.LENGTH_SHORT).show();
                            }
                        })
        );

    }

    public void createContact(final String name, final String email) {
        compositeDisposable.add(
                Completable
                        .fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                rowIdInserted = mContactAppDataBase.getContractDAO().addContact(new Contact(name, email));
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            public void onComplete() {
                                Toast.makeText(mApplication, "Contact add successfully: " + rowIdInserted, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(mApplication, "Contact add error", Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }
    public void clear(){
        compositeDisposable.clear();
    }

    public MutableLiveData<List<Contact>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}

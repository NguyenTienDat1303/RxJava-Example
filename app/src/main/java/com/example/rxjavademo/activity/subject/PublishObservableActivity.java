package com.example.rxjavademo.activity.subject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.operator.BasicActivity;
import com.example.rxjavademo.activity.operator.BufferActivity;
import com.example.rxjavademo.activity.operator.ConcatMapActivity;
import com.example.rxjavademo.activity.operator.CreateActivity;
import com.example.rxjavademo.activity.operator.DistinctActivity;
import com.example.rxjavademo.activity.operator.FilterActivity;
import com.example.rxjavademo.activity.operator.FlatMapActivity;
import com.example.rxjavademo.activity.operator.FromArrayActivity;
import com.example.rxjavademo.activity.operator.MapActivity;
import com.example.rxjavademo.activity.operator.RangeActivity;
import com.example.rxjavademo.activity.operator.SkipActivity;
import com.example.rxjavademo.activity.operator.SkipLastActivity;
import com.example.rxjavademo.model.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class PublishObservableActivity extends AppCompatActivity {
    private TextView tvLog;
    private String txtLog = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        tvLog = findViewById(R.id.tvLog);
        tvLog.setMovementMethod(new ScrollingMovementMethod());
        publishSubject();
    }

    void publishSubject() {
        Observable<String> observable = Observable.just("Java", "Kotlin", "XML", "JSON");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(getObserverFirst());
        publishSubject.subscribe(getObserverSecond());
        publishSubject.subscribe(getObserverThird());
        observable.subscribe(publishSubject);
    }


    private Observer<String> getObserverFirst() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Utils.TAG, "myObserver1: onSubscribe invoked: ");
                txtLog += "myObserver1: onSubscribe invoked: \n";
            }

            @Override
            public void onNext(String s) {
                Log.i(Utils.TAG, "onNext invoked: " + s);
                txtLog += "myObserver1: onNext invoked: " + s + "\n";
                tvLog.setText(txtLog);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Utils.TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(Utils.TAG, "onComplete invoked: ");
                txtLog += "myObserver1: onComplete invoked: " + "\n";
                tvLog.setText(txtLog);
            }
        };
    }

    private Observer<String> getObserverSecond() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Utils.TAG, "myObserver2: onSubscribe invoked: ");
                txtLog += "myObserver2: onSubscribe invoked: \n";
            }

            @Override
            public void onNext(String s) {
                Log.i(Utils.TAG, "onNext invoked: " + s);
                txtLog += "myObserver2: onNext invoked: " + s + "\n";
                tvLog.setText(txtLog);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Utils.TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(Utils.TAG, "onComplete invoked: ");
                txtLog += "myObserver2: onComplete invoked: " + "\n";
                tvLog.setText(txtLog);
            }
        };
    }

    private Observer<String> getObserverThird() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Utils.TAG, "myObserver3: onSubscribe invoked: ");
                txtLog += "myObserver3: onSubscribe invoked: \n";
            }

            @Override
            public void onNext(String s) {
                Log.i(Utils.TAG, "onNext invoked: " + s);
                txtLog += "myObserver3: onNext invoked: " + s + "\n";
                tvLog.setText(txtLog);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Utils.TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(Utils.TAG, "onComplete invoked: ");
                txtLog += "myObserver3: onComplete invoked: " + "\n";
                tvLog.setText(txtLog);
            }
        };
    }
}

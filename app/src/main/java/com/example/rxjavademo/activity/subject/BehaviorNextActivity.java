package com.example.rxjavademo.activity.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavademo.R;
import com.example.rxjavademo.model.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class BehaviorNextActivity extends AppCompatActivity {
    private TextView tvLog;
    private String txtLog = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        tvLog = findViewById(R.id.tvLog);
        tvLog.setMovementMethod(new ScrollingMovementMethod());
        behaviorSubject();
    }

    void behaviorSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(getObserverFirst());
        behaviorSubject.onNext("Java");
        behaviorSubject.onNext("Kotlin");
        behaviorSubject.onNext("XML");
        behaviorSubject.subscribe(getObserverSecond());
        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();
        behaviorSubject.subscribe(getObserverThird());
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

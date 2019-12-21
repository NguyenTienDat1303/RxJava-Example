package com.example.rxjavademo.activity.operator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavademo.R;
import com.example.rxjavademo.model.Utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FilterActivity extends AppCompatActivity {
    private Observable<Integer> myObservable;
    private DisposableObserver<Integer> myObserver;
    private TextView tvLog;
    private String txtLog = "";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        tvLog = findViewById(R.id.tvLog);
        tvLog.setMovementMethod(new ScrollingMovementMethod());
        myObservable = Observable.range(1,20);
        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer%3==0;
                            }
                        })
                        .subscribeWith(getObserver())
        );
    }

    private DisposableObserver<Integer> getObserver() {
        return myObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer s) {
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
                Log.i(Utils.TAG, "onComplete invoked: " );
                txtLog += "myObserver1: onComplete invoked: "+ "\n";
                tvLog.setText(txtLog);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}

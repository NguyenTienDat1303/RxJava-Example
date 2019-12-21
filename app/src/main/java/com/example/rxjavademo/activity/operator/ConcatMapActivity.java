package com.example.rxjavademo.activity.operator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavademo.R;
import com.example.rxjavademo.model.Student;
import com.example.rxjavademo.model.Utils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ConcatMapActivity extends AppCompatActivity {
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView tvLog;
    private String txtLog = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        tvLog = findViewById(R.id.tvLog);
        tvLog.setMovementMethod(new ScrollingMovementMethod());

        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                ArrayList<Student> students = Utils.getStudents();
                for(Student student:students){
                    emitter.onNext(student);
                }
                emitter.onComplete();
            }
        });
        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(new Function<Student, ObservableSource<Student>>() {
                            @Override
                            public ObservableSource<Student> apply(Student student) throws Exception {
                                Student student1 = new Student();
                                student1.setName(student.getName());
                                Student student2 = new Student();
                                student2.setName("New member 1: "+ student.getName());
                                Student student3 = new Student();
                                student3.setName("New member 2: "+ student.getName());
                                return Observable.just(student1, student2, student3);
                            }
                        })
                        .subscribeWith(getObserver())
        );
    }

    private DisposableObserver<Student> getObserver() {
        return myObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student s) {
                Log.i(Utils.TAG, "onNext invoked: " + s);
                txtLog += "-----> myObserver1: onNext invoked: " + s + "\n";
                tvLog.setText(txtLog);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Utils.TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(Utils.TAG, "onComplete invoked: " );
                txtLog += "-----> myObserver1: onComplete invoked: "+ "\n";
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

package com.example.rxjavademo.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.subject.AsyncNextActivity;
import com.example.rxjavademo.activity.subject.AsyncObservableActivity;
import com.example.rxjavademo.activity.subject.BehaviorNextActivity;
import com.example.rxjavademo.activity.subject.BehaviorObservableActivity;
import com.example.rxjavademo.activity.subject.PublishNextActivity;
import com.example.rxjavademo.activity.subject.PublishObservableActivity;
import com.example.rxjavademo.activity.subject.ReplayNextActivity;
import com.example.rxjavademo.activity.subject.ReplayObservableActivity;
import com.example.rxjavademo.model.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class SubjectMenuActivity extends AppCompatActivity {
    ListView lvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMenu = findViewById(R.id.lvMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Utils.getSubjectMenu()
        );
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        navigation(AsyncObservableActivity.class);
                        break;
                    case 1:
                        navigation(AsyncNextActivity.class);
                        break;
                    case 2:
                        navigation(BehaviorObservableActivity.class);
                        break;
                    case 3:
                        navigation(BehaviorNextActivity.class);
                        break;
                    case 4:
                        navigation(PublishObservableActivity.class);
                        break;
                    case 5:
                        navigation(PublishNextActivity.class);
                        break;
                    case 6:
                        navigation(ReplayObservableActivity.class);
                        break;
                    case 7:
                        navigation(ReplayNextActivity.class);
                        break;
                }
            }
        });
    }

    private <T extends Activity> void navigation(Class<T> stx) {
        Intent intent = new Intent(this, stx);
        startActivity(intent);
    }
}

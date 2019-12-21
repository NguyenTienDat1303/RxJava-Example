package com.example.rxjavademo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.dagger2.activity.Dagger2Activity;
import com.example.rxjavademo.activity.room.RoomActivity;
import com.example.rxjavademo.activity.tmdb_project.MovieActivity;
import com.example.rxjavademo.activity.todo_project.activity.ToDoMainActivity;
import com.example.rxjavademo.model.Utils;

public class MainActivity extends AppCompatActivity {
    ListView lvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMenu = findViewById(R.id.lvMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Utils.getMenu()
        );
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        navigation(OperatorMenuActivity.class);
                        break;
                    case 1:
                        navigation(SubjectMenuActivity.class);
                        break;
                    case 2:
                        navigation(RxBindingActivity.class);
                        break;
                    case 3:
                        navigation(ToDoMainActivity.class);
                        break;
                    case 4:
                        navigation(MovieActivity.class);
                        break;
                    case 5:
                        navigation(RoomActivity.class);
                        break;
                    case 6:
                        navigation(Dagger2Activity.class);
                        break;
                }
            }
        });
    }

    private <T extends Activity> void navigation(Class<T> stx) {
        Intent intent = new Intent(MainActivity.this, stx);
        startActivity(intent);
    }
}

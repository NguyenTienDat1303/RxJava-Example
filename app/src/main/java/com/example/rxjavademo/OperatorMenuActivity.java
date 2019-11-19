package com.example.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxjavademo.model.Utils;

public class OperatorMenuActivity extends AppCompatActivity {
    ListView lvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMenu = findViewById(R.id.lvMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Utils.getOperatorMenu()
        );
        lvMenu.setAdapter(adapter);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        navigation(BasicActivity.class);
                        break;
                    case 1:
                        break;
                    case 2:
                        navigation(FromArrayActivity.class);
                        break;
                    case 3:
                        navigation(RangeActivity.class);
                        break;
                    case 4:
                        navigation(CreateActivity.class);
                        break;
                    case 5:
                        navigation(MapActivity.class);
                        break;
                    case 6:
                        navigation(FlatMapActivity.class);
                        break;
                    case 7:
                        navigation(ConcatMapActivity.class);
                        break;
                    case 8:
                        navigation(BufferActivity.class);
                        break;
                    case 9:
                        navigation(FilterActivity.class);
                        break;
                    case 10:
                        navigation(DistinctActivity.class);
                        break;
                    case 11:
                        navigation(SkipActivity.class);
                        break;
                    case 12:
                        navigation(SkipLastActivity.class);
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

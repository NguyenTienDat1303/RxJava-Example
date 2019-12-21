package com.example.rxjavademo.activity.todo_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.rxjavademo.R;
import com.example.rxjavademo.activity.todo_project.adapter.ToDoPagerAdapter;
import com.example.rxjavademo.activity.todo_project.fragment.TodolistFragment;
import com.example.rxjavademo.activity.todo_project.fragment.UpdateToDoFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ToDoMainActivity extends AppCompatActivity implements TodolistFragment.OnFragmentInteractionListener, UpdateToDoFragment.OnFragmentInteractionListener {
    Toolbar toolbar;
    TabLayout todoTabs;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        toolbar = findViewById(R.id.toolbar);
        todoTabs = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.todo_pager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My To Do List");

        todoTabs.addTab(todoTabs.newTab().setIcon(R.drawable.ic_todo));
        todoTabs.addTab(todoTabs.newTab().setIcon(R.drawable.ic_done));
        todoTabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final ToDoPagerAdapter adapter = new ToDoPagerAdapter
                (getSupportFragmentManager(), todoTabs.getTabCount(), 2);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(todoTabs));
        todoTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}

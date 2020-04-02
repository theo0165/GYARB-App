package com.example.theo.todoappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.theo.todoappjava.Models.TodoItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainScreen extends AppCompatActivity {
    private static final String TAG = "com.example.theo.todoappjava";

    TabLayout tabLayout;
    ViewPager viewPager;

    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FloatingActionButton fabAddBtn = findViewById(R.id.add_btn);
        fabAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActivity = new Intent(MainScreen.this, AddActivity.class);
                startActivityForResult(addActivity, 0);
            }
        });

        FloatingActionButton fabSettingsBtn = (FloatingActionButton) findViewById(R.id.settings_btn);
        fabSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent settingsActivity = new Intent(MainScreen.this, SettingsActivity.class);
                startActivityForResult(settingsActivity, 0);
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.todo_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.completed_tab_title));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            Bundle bundle = data.getExtras();
            if(bundle != null) {
                String name = (String)bundle.get("name");
                String completeDate = (String)bundle.get("completeDate");
                boolean noDeadline = (boolean)bundle.get("noDeadline");
                int categoryId = (int)bundle.get("categoryId");

                adapter.getTodoFragment().getTodoListAdapter().addItem(new TodoItem(name, false, categoryId, completeDate, noDeadline));
            }
        }
    }
}
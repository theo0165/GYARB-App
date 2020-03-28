package com.example.theo.todoappjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;
import com.example.theo.todoappjava.Models.TodoItem;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    private static final String TAG = "com.example.theo.todoappjava";

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FloatingActionButton fabAddBtn = findViewById(R.id.add_btn);
        fabAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActivity = new Intent(MainScreen.this, AddActivity.class);
                startActivity(addActivity);
            }
        });

        FloatingActionButton fabSettingsBtn = (FloatingActionButton) findViewById(R.id.settings_btn);
        fabSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Snackbar.make(view, "Settings", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.todo_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.completed_tab_title));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
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

        final DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<TodoItem> items = db.getTodoItems(false);
        for(int i = 0; i<items.size();i++){
            Log.d(TAG, "onCreate: TODO ITEM:\nName: " + items.get(i).getName() + "\nCategory color: " + db.getCategoryColor(items.get(i).getCategory()) + "\n\n");
        }
    }
}
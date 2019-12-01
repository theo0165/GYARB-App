package com.example.theo.todoappjava;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ScrollView;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";

    ActionBar ab;
    DatePicker datePicker;
    Toolbar toolbar;
    CheckBox noDeadlineCheckbox;
    ScrollView sV;
    FloatingActionButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        saveBtn = (FloatingActionButton) findViewById(R.id.save_btn);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayShowTitleEnabled(false);

        ab.setDisplayHomeAsUpEnabled(true);

        datePicker = (DatePicker) findViewById(R.id.deadline_input);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        noDeadlineCheckbox = (CheckBox) findViewById(R.id.no_deadline_checkbox);

        noDeadlineCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    Log.d(TAG, "onClick: checkbox is checked");
                    datePicker.setVisibility(View.GONE);
                }else{
                    Log.d(TAG, "onClick: Checkbox is NOT checked");
                    datePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        sV = (ScrollView) findViewById(R.id.scrollView);
        sV.scrollTo(0, sV.getBottom());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.back_btn:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

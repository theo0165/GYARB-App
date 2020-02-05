package com.example.theo.todoappjava;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    TodoListAdapter adapter;

    public void setListAdapter(TodoListAdapter _adapter){
        adapter = _adapter;
    }

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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameInput = findViewById(R.id.name_input);
                RadioGroup categoryGroup = findViewById(R.id.category_group);

                int checkedCategory = categoryGroup.getCheckedRadioButtonId();
                RadioButton checkedCategoryButton = findViewById(checkedCategory);



                if(nameInput.getText().toString().matches("")){
                    Snackbar.make(v, "Title is required", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("name", nameInput.getText().toString());
                    setResult(0, intent);

                    //adapter.addItem();
                }

                finish();
            }
        });
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

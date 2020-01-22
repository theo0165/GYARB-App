package com.example.theo.todoappjava.TabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;
import com.example.theo.todoappjava.Helpers.DatabaseOpenHelper;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.R;
import com.example.theo.todoappjava.TodoListAdapter;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class TodoFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public TodoFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View inflatedView = inflater.inflate(R.layout.fragment_todo, container, false);

        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] titles = {"Test item 1", "Test item 2", "Test item 3"};
        String[] dates = {"2020-01-01", "2020-02-02", "2020-03-03"};

        recyclerView = view.findViewById(R.id.todoList);
        TodoListAdapter todoListAdapter = new TodoListAdapter(getContext(), titles, dates);

        recyclerView.setAdapter(todoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper db = new DatabaseHelper(getContext());
        db.open();
        Log.d(TAG, "onViewCreated: Database opened");
        ArrayList<TodoItem> items = db.getTodoItems(false);

        //TextView dbTextView = (TextView)view.findViewById(R.id.t);

        /*if(items.size() > 0) {
            Log.d(TAG, "onViewCreated: Items found");
            dbTextView.setText("TODO ITEM:\nName: " + items.get(0).getName() + "\nCategory color: " + db.getCategoryColor(items.get(0).getCategory()) + "\n\n");
        }else{
            Log.d(TAG, "onViewCreated: Items not found");
            dbTextView.setText("NO TODO ITEM");
        }

        String catCOlor = db.getCategoryColor(1);

        dbTextView.setText("Category color: " + catCOlor);

        db.close();*/
    }
}

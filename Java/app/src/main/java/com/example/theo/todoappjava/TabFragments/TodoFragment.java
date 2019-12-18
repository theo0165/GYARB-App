package com.example.theo.todoappjava.TabFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.R;

import java.util.ArrayList;

public class TodoFragment extends Fragment {
    public TodoFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View inflatedView = inflater.inflate(R.layout.fragment_todo, container, false);

        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final DatabaseHelper db = new DatabaseHelper(view.getContext());
        ArrayList<TodoItem> items = db.getTodoItems(false);
        TextView dbTextView = (TextView)view.findViewById(R.id.t);

        if(items.size() > 0) {
            dbTextView.setText("TODO ITEM:\nName: " + items.get(0).getName() + "\nCategory color: " + db.getCategoryColor(items.get(0).getCategory()) + "\n\n");
        }else{
            dbTextView.setText("NO TODO ITEMS");
        }
    }
}

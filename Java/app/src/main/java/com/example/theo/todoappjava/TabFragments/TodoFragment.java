package com.example.theo.todoappjava.TabFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;
import com.example.theo.todoappjava.Helpers.DatabaseOpenHelper;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.R;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

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

        DatabaseHelper db = new DatabaseHelper(getContext());
        db.open();
        Log.d(TAG, "onViewCreated: Database opened");
        ArrayList<TodoItem> items = db.getTodoItems(false);

        TextView dbTextView = (TextView)view.findViewById(R.id.t);

        /*if(items.size() > 0) {
            Log.d(TAG, "onViewCreated: Items found");
            dbTextView.setText("TODO ITEM:\nName: " + items.get(0).getName() + "\nCategory color: " + db.getCategoryColor(items.get(0).getCategory()) + "\n\n");
        }else{
            Log.d(TAG, "onViewCreated: Items not found");
            dbTextView.setText("NO TODO ITEM");
        }*/

        String catCOlor = db.getCategoryColor(1);

        dbTextView.setText("Category color: ");

        db.close();
    }
}

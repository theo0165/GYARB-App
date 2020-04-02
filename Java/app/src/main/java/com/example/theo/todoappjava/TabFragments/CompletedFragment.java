package com.example.theo.todoappjava.TabFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theo.todoappjava.CompletedListAdapter;
import com.example.theo.todoappjava.Databases.TodoItemDatabase;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CompletedFragment extends Fragment {
    private RecyclerView recyclerView;
    private CompletedListAdapter completedListAdapter;

    private TodoFragment todoFragment;

    public CompletedFragment(){}

    public void setTodoFragment(TodoFragment todoFragment)  {
        this.todoFragment = todoFragment;
    }

    public CompletedListAdapter getAdapter() {
        return completedListAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_completed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: adding items to list");
        super.onViewCreated(view, savedInstanceState);

        List<TodoItem> items = TodoItemDatabase.getDatabase(getContext()).todoItemDao().getCompletedTodoItems();
        ArrayList<TodoItem> todoItems = new ArrayList<>(items);

        recyclerView = view.findViewById(R.id.completeList);
        completedListAdapter = new CompletedListAdapter(getContext(), todoItems);

        recyclerView.setAdapter(completedListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}

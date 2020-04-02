package com.example.theo.todoappjava.TabFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theo.todoappjava.R;
import com.example.theo.todoappjava.TodoItem;
import com.example.theo.todoappjava.TodoListAdapter;

import java.util.ArrayList;

public class TodoFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TodoListAdapter todoListAdapter;

    private CompletedFragment completedFragment;

    public TodoFragment(){}

    public void setCompletedFragment(CompletedFragment completedFragment) {
        this.completedFragment = completedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View inflatedView = inflater.inflate(R.layout.fragment_todo, container, false);

        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ArrayList<TodoItem> items = new ArrayList<>();
        //items.add(new TodoItem("Test", false, 1, 1, "2020-01-01"));

        // List<TodoItem> items = TodoItemDatabase.getDatabase(getContext()).todoItemDao().getAllTodoItems();
        final ArrayList<TodoItem> items1 = new ArrayList<>();

        /*FirebaseFirestore.getInstance().collection("todos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        String name = (String)document.getData().get("name");
                        String completeDate = (String)document.getData().get("completeDate");
                        boolean noDeadline = (boolean)document.getData().get("noDeadline");
                        long categoryId = (long)document.getData().get("categoryId");
                        boolean completed = (boolean)document.getData().get("completed");

                        items1.add(new TodoItem(name, completed, (int)categoryId, completeDate, noDeadline));
                    }
                }
            }
        });*/

        recyclerView = view.findViewById(R.id.todoList);
        todoListAdapter = new TodoListAdapter(getContext(), items1);
        todoListAdapter.setCompletedFragment(completedFragment);

        recyclerView.setAdapter(todoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public TodoListAdapter getTodoListAdapter() {
        return todoListAdapter;
    }
}

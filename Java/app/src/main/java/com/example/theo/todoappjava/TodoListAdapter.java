package com.example.theo.todoappjava;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.zip.Inflater;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    String[] dTodoText;
    String[] dTodoDate;
    Context context;

    public TodoListAdapter(Context c, String[] todoText, String[] todoDate){
        context = c;
        dTodoText = todoText;
        dTodoDate = todoDate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.todo_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTodoText.setText(dTodoText[i]);
        viewHolder.mTodoDate.setText(dTodoDate[i]);
    }

    @Override
    public int getItemCount() {
        return dTodoText.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox mCheckbox;
        TextView mTodoText, mTodoDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCheckbox = itemView.findViewById(R.id.completeCheckbox);
            mTodoText = itemView.findViewById(R.id.todoText);
            mTodoDate = itemView.findViewById(R.id.completeDate);
        }
    }
}

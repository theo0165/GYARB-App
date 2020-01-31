package com.example.theo.todoappjava;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;
import com.example.theo.todoappjava.Models.TodoItem;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    public ArrayList<TodoItem> dItems;
    public Context context;

    public TodoListAdapter(Context c, ArrayList<TodoItem> items){
        context = c;
        dItems = items;
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
        viewHolder.mTodoText.setText(dItems.get(i).getName());
        viewHolder.mTodoDate.setText(dItems.get(i).getCompleteDate());

        viewHolder.itemView.setTag(dItems.get(i).getId());
        viewHolder.mCheckbox.setTag(dItems.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return dItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox mCheckbox;
        TextView mTodoText, mTodoDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCheckbox = itemView.findViewById(R.id.completeCheckbox);
            mTodoText = itemView.findViewById(R.id.todoText);
            mTodoDate = itemView.findViewById(R.id.completeDate);

            mCheckbox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.equals(mCheckbox)){
                DatabaseHelper db = new DatabaseHelper(context);
                db.open();
                db.setItemAsComplete(Integer.getInteger(v.getTag().toString()));
                db.close();

                removeAt(getAdapterPosition());
            }
        }
    }

    public void removeAt(int position){
        dItems.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dItems.size());
    }

    public void addItem(TodoItem item){
        dItems.add(item);

        DatabaseHelper db = new DatabaseHelper(context);
        db.open();
        db.addTodoItem(item);
        db.close();

        notifyItemInserted(dItems.size() - 1);
    }
}

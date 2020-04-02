package com.example.theo.todoappjava;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theo.todoappjava.Databases.TodoItemDatabase;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.TabFragments.CompletedFragment;

import java.util.ArrayList;


public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    public ArrayList<TodoItem> dItems;
    public Context context;

    private CompletedFragment completedFragment;

    public TodoListAdapter(Context c, ArrayList<TodoItem> items){
        context = c;
        dItems = items;
    }

    public void setCompletedFragment(CompletedFragment completedFragment) {
        this.completedFragment = completedFragment;
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
        viewHolder.mTodoText.setText(dItems.get(i).name);

        if(dItems.get(i).noDeadline){
            viewHolder.mTodoDate.setText("");
        }else{
            viewHolder.mTodoDate.setText(dItems.get(i).completeDate);
        }

        if(dItems.get(i).category == R.id.category_none){
            viewHolder.mCategoryBox.setVisibility(View.INVISIBLE);
        }else if(dItems.get(i).category == R.id.category_green){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_green));
        }else if(dItems.get(i).category == R.id.category_red){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_red));
        }else if(dItems.get(i).category == R.id.category_orange){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_orange));
        }else if(dItems.get(i).category == R.id.category_blue) {
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_blue));
        }

        viewHolder.itemView.setTag(dItems.get(i).id);
        viewHolder.mCheckbox.setTag(dItems.get(i).id);
    }

    @Override
    public int getItemCount() {
        return dItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox mCheckbox;
        TextView mTodoText, mTodoDate;
        View mCategoryBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCheckbox = itemView.findViewById(R.id.completeCheckbox);
            mTodoText = itemView.findViewById(R.id.todoText);
            mTodoDate = itemView.findViewById(R.id.completeDate);
            mCategoryBox = itemView.findViewById(R.id.category_box);

            mCheckbox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.equals(mCheckbox)){
                int itemID = Integer.parseInt(v.getTag().toString());
                TodoItemDatabase.getDatabase(context).todoItemDao().setItemAsComplete(itemID);
                TodoItem item = dItems.get(getAdapterPosition());

                completedFragment.getAdapter().addItem(item);

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
        TodoItemDatabase.getDatabase(context).todoItemDao().addTodoItem(item);

        notifyItemInserted(dItems.size() - 1);
        notifyDataSetChanged();
    }
}

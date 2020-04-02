package com.example.theo.todoappjava;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.theo.todoappjava.Databases.TodoItemDatabase;
import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.TabFragments.CompletedFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class CompletedListAdapter extends RecyclerView.Adapter<CompletedListAdapter.ViewHolder> {
    private ArrayList<TodoItem> dItems;
    private Context context;

    public CompletedListAdapter(Context c, ArrayList<TodoItem> items){
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
        viewHolder.mTodoText.setText(dItems.get(i).name);

        if(dItems.get(i).noDeadline){
            viewHolder.mTodoDate.setText("");
        }else{
            viewHolder.mTodoDate.setText(dItems.get(i).completeDate);
        }

        Log.d(TAG, "CATEGORY ID: " + dItems.get(i).category);

        if(dItems.get(i).category == 0){
            viewHolder.mCategoryBox.setVisibility(View.INVISIBLE);
        }else if(dItems.get(i).category == 1){
            viewHolder.mCategoryBox.setBackground(ContextCompat.getDrawable(context, R.drawable.radio_green_background));
        }else if(dItems.get(i).category == 2){
            viewHolder.mCategoryBox.setBackground(ContextCompat.getDrawable(context, R.drawable.radio_red_background));
        }else if(dItems.get(i).category == 3){
            viewHolder.mCategoryBox.setBackground(ContextCompat.getDrawable(context, R.drawable.radio_orange_background));
        }else if(dItems.get(i).category == 4) {
            viewHolder.mCategoryBox.setBackground(ContextCompat.getDrawable(context, R.drawable.radio_blue_background));
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
        RadioButton mCategoryBox;

        ViewHolder(@NonNull View itemView) {
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
                removeAt(getAdapterPosition());
            }
        }
    }

    private void removeAt(int position) {
        dItems.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dItems.size());
    }

    public void addItemFromDatabase(int id) {
        Log.d("TESTING", "Id: " + id);
        TodoItem item = TodoItemDatabase.getDatabase(context).todoItemDao().getItem(id);
        Log.d("TESTING", item.name);

        //dItems.add();
    }

    public void addItem(TodoItem item) {
        Log.d("TESTING", item.name);

        dItems.add(item);
        notifyItemInserted(dItems.size() - 1);
    }
}
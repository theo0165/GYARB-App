package com.example.theo.todoappjava;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theo.todoappjava.TabFragments.CompletedFragment;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    public ArrayList<TodoItem> dItems;
    public Context context;

    private CompletedFragment completedFragment;

    public TodoListAdapter(Context c, ArrayList<TodoItem> items){
        context = c;
        dItems = items;

        FirebaseFirestore.getInstance().collection("todos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
                    if(change.getType() == DocumentChange.Type.ADDED) {
                        Map<String, Object> data = change.getDocument().getData();
                        Log.d("TESTING", "DATA: " + data);
                        String name = (String)data.get("name");
                        String completeDate = (String)data.get("completeDate");
                        boolean noDeadline = (boolean)data.get("noDeadline");
                        long categoryId = (long)data.get("categoryId");
                        boolean completed = (boolean)data.get("completed");

                        if(!completed)
                            dItems.add(new TodoItem(change.getDocument().getId(), name, completeDate, noDeadline, (int)categoryId, completed));

                        notifyDataSetChanged();
                    } else if(change.getType() == DocumentChange.Type.MODIFIED) {
                        Log.d("TESTING", "ID: " + change.getDocument().getId() + " : " + change.getDocument().getData());
                        String id = change.getDocument().getId();

                        Map<String, Object> data = change.getDocument().getData();
                        String name = (String)data.get("name");
                        String completeDate = (String)data.get("completeDate");
                        boolean noDeadline = (boolean)data.get("noDeadline");
                        long categoryId = (long)data.get("categoryId");
                        boolean completed = (boolean)data.get("completed");

                        if(completed) {
                            int index = getItemIndexWithId(id);
                            if(index != -1) {
                                dItems.remove(index);
                                notifyItemRemoved(index);
                            }
                        } else {
                            if(hasItemWithId(id)) {
                                int index = getItemIndexWithId(id);
                                TodoItem item = dItems.get(index);
                                item.name = name;
                                item.completeDate = completeDate;
                                item.noDeadline = noDeadline;
                                item.categoryId = (int)categoryId;
                                item.completed = false;

                                notifyItemChanged(index);
                            } else {
                                dItems.add(new TodoItem(change.getDocument().getId(), name, completeDate, noDeadline, (int)categoryId, completed));
                                notifyItemInserted(dItems.size() - 1);
                            }

                        }
                    }
                }
            }
        });
    }

    public void setCompletedFragment(CompletedFragment completedFragment) {
        this.completedFragment = completedFragment;
    }

    public boolean hasItemWithId(String id) {
        for(TodoItem item : dItems) {
            if(item.id.equals(id)) {
                return true;
            }
        }

        return false;
    }

    public int getItemIndexWithId(String id) {
        for(int i = 0; i < dItems.size(); i++) {
            if(dItems.get(i).id.equals(id)) {
                return i;
            }
        }

        return -1;
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

        if(dItems.get(i).categoryId == R.id.category_none){
            viewHolder.mCategoryBox.setVisibility(View.INVISIBLE);
        }else if(dItems.get(i).categoryId == R.id.category_green){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_green));
        }else if(dItems.get(i).categoryId == R.id.category_red){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_red));
        }else if(dItems.get(i).categoryId == R.id.category_orange){
            viewHolder.mCategoryBox.setBackgroundColor(ContextCompat.getColor(context, R.color.radio_orange));
        }else if(dItems.get(i).categoryId == R.id.category_blue) {
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
                String itemID = (String)v.getTag();

                TodoItem item = dItems.get(getItemIndexWithId(itemID));

                Map<String, Object> updateData = new HashMap<>();

                updateData.put("name", item.name);
                updateData.put("completeDate", item.completeDate);
                updateData.put("noDeadline", item.noDeadline);
                updateData.put("categoryId", item.categoryId);
                updateData.put("completed", true);

                FirebaseFirestore.getInstance().collection("todos").document(itemID).set(updateData);

                removeAt(getAdapterPosition());
            }
        }
    }

    public void removeAt(int position){
        dItems.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dItems.size());
    }
}

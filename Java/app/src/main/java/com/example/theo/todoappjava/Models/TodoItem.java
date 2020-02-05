package com.example.theo.todoappjava.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TodoItem {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public boolean completed;
    public int category;
    public String categoryColor;
    public String completeDate;

    public TodoItem(String name, boolean completed, int category, String completeDate){
        this.name = name;
        this.completed = completed;
        this.category = category;
        this.completeDate = completeDate;
    }
}

package com.example.theo.todoappjava.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.theo.todoappjava.Static.Categories;

@Entity
public class TodoItem {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public boolean completed;
    public int category;
    public String completeDate;

    public TodoItem(String name, boolean completed, int category, String completeDate){
        this.name = name;
        this.completed = completed;
        this.category = category;
        this.completeDate = completeDate;
    }
}

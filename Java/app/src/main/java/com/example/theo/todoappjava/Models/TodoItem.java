package com.example.theo.todoappjava.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoItem {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public boolean completed;
    public int category;
    public String completeDate;
    public boolean noDeadline;

    public TodoItem(String name, boolean completed, int category, String completeDate, boolean noDeadline){
        this.name = name;
        this.completed = completed;
        this.category = category;
        this.completeDate = completeDate;
        this.noDeadline = noDeadline;
    }
}

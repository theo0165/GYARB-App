package com.example.theo.todoappjava.Models;

import com.example.theo.todoappjava.Helpers.DatabaseHelper;

public class TodoItem {
    private int _id;
    private String _name;
    private boolean _completed;
    private int _category;
    private String _categoryColor;

    public TodoItem(String name, boolean completed, int category, int id){
        // ID MAY BE NULL IF ITEM HAS NOT BEEN CREATED
        _id = id;
        _name = name;
        _completed = completed;
        _category = category;
    }

    public int getId() { return _id; }

    public void setId(int id) { this._id = id; }

    public String getName() { return _name; }

    public void setName(String name) { this._name = name; }

    public boolean isCompleted() { return _completed; }

    public void setCompleted(boolean completed) { this._completed = completed; }

    public int getCategory() { return _category; }

    public void setCategory(int category) { this._category = category; }
}

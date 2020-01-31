package com.example.theo.todoappjava.Models;

public class TodoItem {
    private int _id;
    private String _name;
    private boolean _completed;
    private int _category;
    private String _categoryColor;
    private String _completeDate;

    public TodoItem(String name, boolean completed, int category, int id, String completeDate){
        // ID MAY BE NULL IF ITEM HAS NOT BEEN CREATED
        _id = id;
        _name = name;
        _completed = completed;
        _category = category;
        _completeDate = completeDate;
    }

    public int getId() { return _id; }

    public void setId(int id) { this._id = id; }

    public String getName() { return _name; }

    public void setName(String name) { this._name = name; }

    public boolean isCompleted() { return _completed; }

    public void setCompleted(boolean completed) { this._completed = completed; }

    public int getCategory() { return _category; }

    public void setCategory(int category) { this._category = category; }

    public String getCompleteDate(){ return _completeDate; }
}

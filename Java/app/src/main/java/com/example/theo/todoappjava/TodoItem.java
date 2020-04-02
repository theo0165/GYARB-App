package com.example.theo.todoappjava;

public class TodoItem {

    public String id;
    public String name;
    public String completeDate;
    public boolean noDeadline;
    public int categoryId;
    public boolean completed;

    public TodoItem(String id, String name, String completeDate, boolean noDeadline, int categoryId, boolean completed) {
        this.id = id;
        this.name = name;
        this.completeDate = completeDate;
        this.noDeadline = noDeadline;
        this.categoryId = categoryId;
        this.completed = completed;
    }

    public TodoItem(String name, String completeDate, boolean noDeadline, int categoryId, boolean completed) {
        this.id = "";
        this.name = name;
        this.completeDate = completeDate;
        this.noDeadline = noDeadline;
        this.categoryId = categoryId;
        this.completed = completed;
    }



}

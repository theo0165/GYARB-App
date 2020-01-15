package com.example.theo.todoappjava.Models;

public class Category {
    private int _id;
    private String _color;

    public Category(int id, String color){
        _id = id;
        _color = color;
    }

    public int getId(){ return _id; }

    public String getColor(){ return _color; }
}

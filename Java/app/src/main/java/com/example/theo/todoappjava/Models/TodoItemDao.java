package com.example.theo.todoappjava.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TodoItemDao {

    @Insert
    void addTodoItem(TodoItem item);

    @Query("SELECT * FROM todoitem")
    List<TodoItem> getAllTodoItems();
}

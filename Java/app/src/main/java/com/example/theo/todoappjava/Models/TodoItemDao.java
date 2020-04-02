package com.example.theo.todoappjava.Models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoItemDao {

    @Insert
    void addTodoItem(TodoItem item);

    @Query("SELECT * FROM todoitem WHERE completed = '0'")
    List<TodoItem> getAllTodoItems();

    @Query("SELECT * FROM todoitem WHERE completed = '1'")
    List<TodoItem> getCompletedTodoItems();

    @Query("SELECT * FROM todoItem WHERE id=:id")
    TodoItem getItem(int id);

    @Query("DELETE FROM todoitem WHERE id=:id")
    void removeTodoItem(int id);

    @Query("UPDATE todoitem SET completed=1 WHERE id=:id")
    void setItemAsComplete(int id);

    @Query("SELECT completed FROM todoitem WHERE id=:id")
    boolean isItemCompleted(int id);
}

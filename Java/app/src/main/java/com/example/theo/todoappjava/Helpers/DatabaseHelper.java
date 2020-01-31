package com.example.theo.todoappjava.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.theo.todoappjava.Models.TodoItem;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class DatabaseHelper {
    private final Context context;
    private SQLiteDatabase databaseRead;
    private SQLiteDatabase databaseWrite;
    private DatabaseOpenHelper dbHelper;

    public DatabaseHelper(Context context){
        this.context = context;
        dbHelper = new DatabaseOpenHelper(context);
    }

    public DatabaseHelper open() throws SQLException {
        dbHelper.openDatabase();
        dbHelper.close();
        databaseRead = dbHelper.getReadableDatabase();
        databaseWrite = dbHelper.getWritableDatabase();

        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void addTodoItem(TodoItem item){
        ContentValues values = new ContentValues();
        values.put("title", item.getName());
        values.put("category", item.getCategory());

        databaseWrite.insert("todoitem", null, values);
        databaseWrite.close();
    }

    public ArrayList<TodoItem> getTodoItems(boolean completed){
        ArrayList<TodoItem> items = new ArrayList<TodoItem>();
        String query;

        if(completed){
            query = "SELECT * FROM todoitem WHERE completed=1";
        }else{
            query = "SELECT * FROM todoitem WHERE completed=0";
        }
        Log.d(TAG, "getTodoItems: query: " + query);

        Cursor cursor = databaseRead.rawQuery(query, null);
        try{
            Log.d(TAG, "getTodoItems: Starting TRY block");
            if(cursor.moveToFirst()){
                Log.d(TAG, "getTodoItems: MOVING TO FIRST ITEM IN DATABASE");
                do{
                    Log.d(TAG, "getTodoItems: MOVING TO NEXT ITEM IN DATABASE");
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    int category = cursor.getInt(cursor.getColumnIndex("category"));
                    boolean isCompleted;

                    if(cursor.getInt(cursor.getColumnIndex("completed")) == 1){
                        isCompleted = true;
                    }else{
                        isCompleted = false;
                    }
                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "getTodoItems: Error while trying to retrive todo items");
        }finally {
            Log.d(TAG, "getTodoItems: Closing cursor");
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return items;
    }

    public String getCategoryColor(int id){
        String query = "SELECT color FROM category WHERE id=" + id;
        Cursor cursor = databaseRead.rawQuery(query, null);

        cursor.moveToFirst();

        String color = cursor.getString(0);
        cursor.close();

        return color;
    }

    public void setItemAsComplete(int id){
        String query = "UPDATE todoitem SET complete=1 WHERE id=" + id;
        Cursor cursor = databaseWrite.rawQuery(query, null);

        cursor.close();
    }
}

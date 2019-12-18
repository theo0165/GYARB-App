package com.example.theo.todoappjava.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.theo.todoappjava.Models.TodoItem;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todoapp";

    public DatabaseHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "CREATE TABLE category (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "color INTEGER NOT NULL" +
                ");";

        db.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_TODO_ITEM_TABLE = "CREATE TABLE todoitem (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "title TEXT NOT NULL," +
                "completed INTEGER NOT NULL DEFAULT 0," +
                "category INTEGER," +
                "FOREIGN KEY(category) REFERENCES category(id)" +
                ");";

        db.execSQL(CREATE_TODO_ITEM_TABLE);

        String CREATE_OPTIONS_TABLE = "CREATE TABLE options (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "opt_key TEXT NOT NULL," +
                "opt_value TEXT NOT NULL" +
                ");";

        db.execSQL(CREATE_OPTIONS_TABLE);

        String INSERT_CATEGORY = "INSERT INTO category (color) VALUES " +
                "('#3c40c6'), " +
                "('#05c46b'), " +
                "('#ff3f34'), " +
                "('#ffa801'), " +
                "('#485460'), " +
                "('#ffffff');";

        db.execSQL(INSERT_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS todoitem");
        db.execSQL("DROP TABLE IF EXISTS options");

        onCreate(db);
    }

    public void addTodoItem(TodoItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getName());
        values.put("category", item.getCategory());

        db.insert("todoitem", null, values);
        db.close();
    }

    public ArrayList<TodoItem> getTodoItems(boolean completed){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TodoItem> items = new ArrayList<TodoItem>();
        String query;

        if(completed){
            query = "SELECT * FROM todoitem WHERE completed=1";
        }else{
            query = "SELECT * FROM todoitem WHERE completed=0";
        }

        Cursor cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    int category = cursor.getInt(cursor.getColumnIndex("category"));
                    boolean isCompleted;

                    if(cursor.getInt(cursor.getColumnIndex("completed")) == 1){
                        isCompleted = true;
                    }else{
                        isCompleted = false;
                    }

                    items.add(new TodoItem(title, isCompleted, category, id));
                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "getTodoItems: Error while trying to retrive todo items");
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return items;
    }

    public String getCategoryColor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT color FROM category WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        String color = cursor.getString(0);
        cursor.close();

        return color;
    }
}

package com.example.theo.todoappjava.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.theo.todoappjava.TodoItem;

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

    void addTodoItem(TodoItem item){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("category", item.getCategory());

        db.insert("todoitem", null, values);
        db.close();
    }
}

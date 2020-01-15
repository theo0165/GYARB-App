package com.example.theo.todoappjava.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.theo.todoappjava.Models.TodoItem;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";
    private static final String DATABASE_SUB_PATH = "/databases/" + DATABASE_NAME;
    private static String APP_DATA_PATH = "";
    private SQLiteDatabase dataBase;
    private final Context context;

    public DatabaseOpenHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        APP_DATA_PATH = c.getApplicationInfo().dataDir;
        this.context = c;
    }

    public boolean openDatabase() throws SQLException{
        String mPath = APP_DATA_PATH + DATABASE_SUB_PATH;
        dataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);

        return dataBase != null;
    }

    @Override
    public synchronized void close(){
        if(dataBase != null){
            dataBase.close();
        }
        super.close();
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
}

package com.example.theo.todoappjava.Databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.theo.todoappjava.Models.TodoItem;
import com.example.theo.todoappjava.Models.TodoItemDao;
import com.example.theo.todoappjava.Models.DataTable;
import com.example.theo.todoappjava.Models.DataTableDao;

@Database(entities = {TodoItem.class, DataTable.class}, version = 21, exportSchema = false)
public abstract class TodoItemDatabase extends RoomDatabase {

    public static TodoItemDatabase INSTANCE;

    public abstract TodoItemDao todoItemDao();
    public abstract DataTableDao dataTableDaoDao();

    public static TodoItemDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, TodoItemDatabase.class, "todoitemdatabase")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

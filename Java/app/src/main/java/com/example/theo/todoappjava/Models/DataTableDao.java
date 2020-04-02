package com.example.theo.todoappjava.Models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DataTableDao {
    @Insert
    void addValue(DataTable dataTable);

    @Query("SELECT value FROM datatable WHERE `key`=:key")
    String getValueFromKey(String key);

    @Query("UPDATE datatable SET value=:value WHERE `key`=:key")
    void updateValue(String key, String value);

    @Query("DELETE FROM datatable WHERE `key`=:key")
    void removeData(String key);
}

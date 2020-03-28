package com.example.theo.todoappjava.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "datatable")
public class DataTable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public String value;

    public DataTable(String key, String value){
        this.key = key;
        this.value = value;
    }
}

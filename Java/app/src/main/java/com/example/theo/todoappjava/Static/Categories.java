package com.example.theo.todoappjava.Static;

public enum Categories {
    NONE("#eeeeee", 0),
    GREEN("#27ae60", 1),
    RED("#c0392b", 2),
    ORANGE("#e67e22", 3),
    BLUE("#2980b9", 4);

    private String color;
    private int id;

    Categories(String color, int id){
        this.color = color;
        this.id = id;
    }

    public String getColor(){
        return this.color;
    }

    public int getId(){
        return this.id;
    }
}

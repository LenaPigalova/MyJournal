package com.example.asus.myjournal;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ASUS on 24.08.2018.
 */

public class Predmet {
    private int idOfPredmet;
    private String name;

    /*public Predmet(String name, int idOfPredmet){
        this.idOfPredmet = idOfPredmet;
        this.name = name;
    }*/

    public int getIdOfPredmet() {
        return idOfPredmet;
    }

    public void setIdOfPredmet(int idOfPredmet) {
        this.idOfPredmet = idOfPredmet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

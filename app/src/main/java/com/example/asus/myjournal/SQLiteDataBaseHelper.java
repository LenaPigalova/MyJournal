package com.example.asus.myjournal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 24.08.2018.
 */

public class SQLiteDataBaseHelper extends SQLiteOpenHelper{

    private static String DB_Name = "myJournal.db";
    private static int Version = 1;
    private String DB_Path;
    private Context context;
    private SQLiteDatabase db;

    SQLiteDataBaseHelper(Context context){
        super(context, DB_Name, null, Version);
        DB_Path = context.getFilesDir().getPath() + DB_Name;
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        if (db==null){
            db = context.openOrCreateDatabase(DB_Name, Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS lesson (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXIST groups (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, idPredmet INTEGER)");
            db.execSQL("CREATE TABLE IF NOT EXIST students (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, idGroup INTEGER, comment TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXIST themes (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date DATE, idGroup INTEGER)");
            db.execSQL("CREATE TABLE IF NOT EXIST marks (_id INTEGER PRIMARY KEY AUTOINCREMENT, mark TEXT, date DATE, idStudent INTEGER)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lesson");
        onCreate(db);

    }
}

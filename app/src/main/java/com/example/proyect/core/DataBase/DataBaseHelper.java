package com.example.proyect.core.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataBaseHelper extends SQLiteOpenHelper {

    public   static final String DB_NOM = "eventos.sqlie";
    private static final int VERSION = 2;

    // Table Name
    public static final String TABLE_NAME = "eventos";

    // Table columns
    public static final String _ID = "_id";
    public static final String DESC = "descripcion";
    public static final String CAUSE = "causa";
    public static final String SERVICE = "ServicioAfectado";
    public static final String DATETIMEINIT = "fechaIni";
    public static final String DATETIMEFINISH = "FechaFin";
    public static final String AVAILABLE = "indisponibildad";


    private static final String CREATE_TABLE =  "create table " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY NOT NULL, "
            + DESC + " TEXT NOT NULL, "
            + CAUSE + " TEXT NOT NULL, "
            + SERVICE + " TEXT NOT NULL, "
            + DATETIMEINIT + " TEXT NOT NULL, "
            +  DATETIMEFINISH + " TEXT NOT NULL, "
            + AVAILABLE + " TEXT NOT NULL )";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NOM, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        System.out.println("BASE DE DATES MCCREADY");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists eventos");
        onCreate(sqLiteDatabase);
        System.out.println("BASE DE DATES YA DISPOSABLE");
    }


}

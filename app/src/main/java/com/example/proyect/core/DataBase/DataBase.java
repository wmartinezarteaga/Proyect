package com.example.proyect.core.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataBase extends SQLiteOpenHelper {

    private  static final String DB_NOM = "eventos.sqlie";
    private static final int VERSION = 2;


    private static final String CREATE_TABLE = "create table eventos(" +
            "id INTEGER PRIMARY KEY, " +
            "fechaIni TEXT, descripción TEXT," +
            " causa TEXT, ServicioAfectado TEXT," +
            " FechaFin TEXT, indisponibildad TEXT)";



    public DataBase(@Nullable Context context) {
        super(context, DB_NOM, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE );
        System.out.println("base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists eventos");

        onCreate(sqLiteDatabase);
        System.out.println("base existente disponible ");
    }


}

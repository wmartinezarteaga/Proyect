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
    public static final String TABLE_NAME2 = "localizacion";

    // Table columns
    public static final String _ID = "_id";
    public static final String DESC = "descripcion";
    public static final String CAUSE = "causa";
    public static final String SERVICE = "ServicioAfectado";
    public static final String DATETIMEINIT = "fechaIni";
    public static final String DATETIMEFINISH = "FechaFin";
    public static final String AVAILABLE = "indisponibildad";

    //////////////////////////////******************
    public static final String _ID_LO = "_id_lo";
    public static final String QUERY = "querylo";
    public static final String STATUS = "status";
    public static final String COUNTRY = "country";
    public static final String COUNTRYCODE = "countryCode";
    public static final String REGION = "region";
    public static final String REGIONNAME = "regionName";
    public static final String CITY = "city";
    public static final String ZIP = "zip";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String TIMEZONE = "timezone";
    public static final String ISP = "isp";
    public static final String ORG = "org";
    public static final String AS = "aslo";

    private static final String CREATE_TABLE1 =  "create table " + TABLE_NAME2 + "("
            + _ID_LO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUERY + " TEXT NOT NULL, "
            + STATUS + " TEXT NOT NULL, "
            + COUNTRY + " TEXT NOT NULL, "
            + COUNTRYCODE + " TEXT NOT NULL, "
            +  REGION + " TEXT NOT NULL, "
            + REGIONNAME + " TEXT NOT NULL, "
            + CITY + " TEXT NOT NULL, "
            + ZIP + " TEXT NOT NULL, "
            + LAT + " TEXT NOT NULL, "
            + LON + " TEXT NOT NULL, "
            + TIMEZONE + " TEXT NOT NULL, "
            + ISP + " TEXT NOT NULL, "
            + ORG + " TEXT NOT NULL, "
            + AS + " TEXT NOT NULL )";




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
        sqLiteDatabase.execSQL(CREATE_TABLE1);
        System.out.println("BASE DE DATES MCCREADY");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists eventos");
        onCreate(sqLiteDatabase);
        System.out.println("BASE DE DATES YA DISPOSABLE");
    }


}

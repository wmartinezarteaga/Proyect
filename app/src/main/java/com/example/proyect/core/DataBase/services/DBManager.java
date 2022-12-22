package com.example.proyect.core.DataBase.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.proyect.app.ui.Controller.Message;
import com.example.proyect.core.DataBase.DataBaseHelper;
import com.example.proyect.core.DataBase.models.Localizacion;

import java.sql.SQLException;

public class DBManager {
    private DataBaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertPosision(
            Localizacion posision
    ) {
        String querie= posision.getQuery();
        String status= posision.getStatus();
        String country= posision.getCountry();
        String countrycode= posision.getCountryCode();
        String region= posision.getRegion();
        String regionname= posision.getRegionName();
        String city= posision.getCity();
        String zip= posision.getZip();
        String lat= String.valueOf(posision.getLat());
        String lon= String.valueOf(posision.getLon());
        String time= posision.getTimezone();
        String org= posision.getOrg();
        String as= posision.getAs();
        String isp= posision.getISP();

                ContentValues contentValue = new ContentValues();

        contentValue.put(DataBaseHelper.QUERY, querie);
        contentValue.put(DataBaseHelper.STATUS, status);
        contentValue.put(DataBaseHelper.COUNTRY, country);
        contentValue.put(DataBaseHelper.COUNTRYCODE, countrycode);
        contentValue.put(DataBaseHelper.REGION, region);
        contentValue.put(DataBaseHelper.REGIONNAME, regionname);
        contentValue.put(DataBaseHelper.CITY, city);
        contentValue.put(DataBaseHelper.ZIP, zip);
        contentValue.put(DataBaseHelper.LAT, lat);
        contentValue.put(DataBaseHelper.LON, lon);
        contentValue.put(DataBaseHelper.TIMEZONE, time);
        contentValue.put(DataBaseHelper.ORG, org);
        contentValue.put(DataBaseHelper.AS, as);
        contentValue.put(DataBaseHelper.ISP, isp);
        long si =  database.insert(DataBaseHelper.TABLE_NAME, null, contentValue);
        if(si >-1){
            Message.message(context.getApplicationContext(),"Registro Exitoso de la posision");
        }else if (si == -1){
            Log.e("UPS: " ,  "Oops! Error al insertar");
            Message.message(context.getApplicationContext(),"Oops! Error al insertar");
        }
    }

    public Cursor getAllDataTabeltPosision() throws SQLException {
        String[] columns = new String[] {
                DataBaseHelper._ID,
                DataBaseHelper.STATUS,
                DataBaseHelper.COUNTRY,
                DataBaseHelper.COUNTRYCODE,
                DataBaseHelper.REGION,
                DataBaseHelper.REGIONNAME,
                DataBaseHelper.CITY,
                DataBaseHelper.ZIP,
                DataBaseHelper.LAT,
                DataBaseHelper.LON,
                DataBaseHelper.TIMEZONE,
                DataBaseHelper.ORG,
                DataBaseHelper.AS,
                DataBaseHelper.ISP,
                DataBaseHelper.QUERY
        };
        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int updatePosision(
            Localizacion posision, Integer id
    ) {
        String querie= posision.getQuery();
        String status= posision.getStatus();
        String country= posision.getCountry();
        String countrycode= posision.getCountryCode();
        String region= posision.getRegion();
        String regionname= posision.getRegionName();
        String city= posision.getCity();
        String zip= posision.getZip();
        String lat= String.valueOf(posision.getLat());
        String lon= String.valueOf(posision.getLon());
        String time= posision.getTimezone();
        String org= posision.getOrg();
        String as= posision.getAs();
        String isp= posision.getISP();

        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.QUERY, querie);
        contentValue.put(DataBaseHelper.STATUS, status);
        contentValue.put(DataBaseHelper.COUNTRY, country);
        contentValue.put(DataBaseHelper.COUNTRYCODE, countrycode);
        contentValue.put(DataBaseHelper.REGION, region);
        contentValue.put(DataBaseHelper.REGIONNAME, regionname);
        contentValue.put(DataBaseHelper.CITY, city);
        contentValue.put(DataBaseHelper.ZIP, zip);
        contentValue.put(DataBaseHelper.LAT, lat);
        contentValue.put(DataBaseHelper.LON, lon);
        contentValue.put(DataBaseHelper.TIMEZONE, time);
        contentValue.put(DataBaseHelper.ORG, org);
        contentValue.put(DataBaseHelper.AS, as);
        contentValue.put(DataBaseHelper.ISP, isp);
        int i = database.update(DataBaseHelper.TABLE_NAME, contentValue, DataBaseHelper._ID + " = " + id, null);

        return i;
    }

    public int deletePosision(long _id) {
        int i =   database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper._ID + "=" + _id, null);
        return  i;
    }
}

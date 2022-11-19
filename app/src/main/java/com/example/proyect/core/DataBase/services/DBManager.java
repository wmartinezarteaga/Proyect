package com.example.proyect.core.DataBase.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.proyect.app.ui.Controller.Message;
import com.example.proyect.core.DataBase.DataBaseHelper;
import com.example.proyect.core.DataBase.models.Eventos;

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

    public void insert(
    Eventos evt
    ) {
        Integer _id = evt.getId();
        String descrip= evt.getDescripción();
        String causa= evt.getCausa();
        String service= evt.getServicioAfectado();
        String initDate= evt.getFechaIni();
        String finishDate= evt.getFechaFin();
        String indisponibilidad= evt.getIndisponibildad();

        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper._ID, _id);
        contentValue.put(DataBaseHelper.DESC, descrip);
        contentValue.put(DataBaseHelper.CAUSE, causa);
        contentValue.put(DataBaseHelper.SERVICE, service);
        contentValue.put(DataBaseHelper.DATETIMEINIT, initDate);
        contentValue.put(DataBaseHelper.DATETIMEFINISH, finishDate);
        contentValue.put(DataBaseHelper.AVAILABLE, indisponibilidad);
      long si =  database.insert(DataBaseHelper.TABLE_NAME, null, contentValue);
      if(si >-1){
          Message.message(context.getApplicationContext(),"Registro Exitoso");
      }else if (si == -1){
          Log.e("UPS: " ,  "Oops! Error al insertar");
          Message.message(context.getApplicationContext(),"Oops! Error al insertar");
      }
    }

    public Cursor getAllDataTabelt() throws SQLException {
        String[] columns = new String[] {
                DataBaseHelper._ID,
                DataBaseHelper.DESC,
                DataBaseHelper.CAUSE,
                DataBaseHelper.SERVICE,
                DataBaseHelper.DATETIMEINIT,
                DataBaseHelper.DATETIMEFINISH,
                DataBaseHelper.AVAILABLE,
        };
        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, columns, null, null, null, null, null);
       if(cursor != null){
       cursor.moveToFirst();
   }
        return cursor;
    }

    public int update(
            Eventos evt,Integer id
    ) {
        String descrip= evt.getDescripción();
        String causa= evt.getCausa();
        String service= evt.getServicioAfectado();
        String initDate= evt.getFechaIni();
        String finishDate= evt.getFechaFin();
        String indisponibilidad= evt.getIndisponibildad();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DESC, descrip);
        contentValues.put(DataBaseHelper.CAUSE, causa);
        contentValues.put(DataBaseHelper.SERVICE, service);
        contentValues.put(DataBaseHelper.DATETIMEINIT, initDate);
        contentValues.put(DataBaseHelper.DATETIMEFINISH, finishDate);
        contentValues.put(DataBaseHelper.AVAILABLE, indisponibilidad);
        int i = database.update(DataBaseHelper.TABLE_NAME, contentValues, DataBaseHelper._ID + " = " + id, null);

        return i;
    }

    public int delete(long _id) {
     int i =   database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper._ID + "=" + _id, null);
   return  i;
     }

}

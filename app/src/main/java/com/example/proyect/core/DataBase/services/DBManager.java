package com.example.proyect.core.DataBase.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyect.core.DataBase.DataBaseHelper;

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
            String descrip,
            String causa,
            String service,
            String initDate,
            String finishDate,
            String indisponibilidad

    ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.DESC, descrip);
        contentValue.put(DataBaseHelper.CAUSE, causa);
        contentValue.put(DataBaseHelper.SERVICE, service);
        contentValue.put(DataBaseHelper.DATETIMEINIT, initDate);
        contentValue.put(DataBaseHelper.DATETIMEFINISH, finishDate);
        contentValue.put(DataBaseHelper.AVAILABLE, indisponibilidad);
        database.insert(DataBaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
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
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(
            long _id,
            String descrip,
            String causa,
            String service,
            String initDate,
            String finishDate,
            String indisponibilidad
    ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DESC, descrip);
        contentValues.put(DataBaseHelper.CAUSE, causa);
        contentValues.put(DataBaseHelper.SERVICE, service);
        contentValues.put(DataBaseHelper.DATETIMEINIT, initDate);
        contentValues.put(DataBaseHelper.DATETIMEFINISH, finishDate);
        contentValues.put(DataBaseHelper.AVAILABLE, indisponibilidad);
        int i = database.update(DataBaseHelper.TABLE_NAME, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper._ID + "=" + _id, null);
    }

}

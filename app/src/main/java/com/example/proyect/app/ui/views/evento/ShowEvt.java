package com.example.proyect.app.ui.views.evento;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyect.R;
import com.example.proyect.app.ui.Controller.TabletDynamic;
import com.example.proyect.core.DataBase.services.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ShowEvt extends AppCompatActivity {
  private   DBManager base ;
   private TabletDynamic tabla;
    String[]  header ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_evt);
        Objects.requireNonNull(getSupportActionBar()).setTitle("LOCALIZACIONES");
        base = new DBManager(getBaseContext());
        initDataTable();//inicializa la data de la tabla
    }

    public void initDataTable(){
        tabla = new TabletDynamic(findViewById(R.id.tablet),this,false);
        header = new String[]{"IP", "PAIS", "REGION", "CIUDAD", "LATITUD", "LONGITUD", "ZONA DE T"};
        tabla.addHeader(header);
        try {
            dataLoadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dataLoadTable() throws SQLException {
        base.open();
        Cursor mCur = base.getAllDataTabeltPosision();
        base.close();
        while (!mCur.isAfterLast()) {
            String[] items = {
                    mCur.getString(14),
                    mCur.getString(1),
                    mCur.getString(4),
                    mCur.getString(5),
                    mCur.getString(7),
                    mCur.getString(8),
                    mCur.getString(9)
            };
            ArrayList<String[]> data = new ArrayList<String[]>();
            data.add(items);
            tabla.addData(data);
            mCur.moveToNext();
        }
    }
}
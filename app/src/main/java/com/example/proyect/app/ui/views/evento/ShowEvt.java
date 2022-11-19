package com.example.proyect.app.ui.views.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        Objects.requireNonNull(getSupportActionBar()).setTitle("Eventos registrados");
        base = new DBManager(getBaseContext());
        initDataTable();//inicializa la data de la tabla
    }

    public void initDataTable(){
        tabla = new TabletDynamic(findViewById(R.id.tablet),this,false);
        header = new String[]{"ID", "DESCRIPCION", "CAUSA", "SERVICIO", "FECHA INICIAL", "FECHA FINAL", "INDISPONIBILIDAD"};
        tabla.addHeader(header);
        try {
            dataLoadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dataLoadTable() throws SQLException {
        base.open();
        Cursor mCur = base.getAllDataTabelt();
        base.close();
        while (!mCur.isAfterLast()) {
            String[] items = {
                    mCur.getString(0),
                    mCur.getString(1),
                    mCur.getString(2),
                    mCur.getString(3),
                    mCur.getString(4),
                    mCur.getString(5),
                    mCur.getString(6)
            };
            ArrayList<String[]> data = new ArrayList<String[]>();
            data.add(items);
            tabla.addData(data);
            mCur.moveToNext();
        }

    }
}
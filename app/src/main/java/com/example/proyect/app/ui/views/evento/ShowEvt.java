package com.example.proyect.app.ui.views.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.proyect.R;
import com.example.proyect.app.ui.Controller.TabletDynamic;
import com.example.proyect.core.DataBase.services.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowEvt extends AppCompatActivity {
  private  TableLayout tableLayout;
    DBManager base ;
    TabletDynamic tabla;
    String[]  header ;
    ArrayList<String[]>  currenListEvt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_evt);
        base = new DBManager(getBaseContext());
        currenListEvt = new ArrayList<String[]>();
        initDataTable();//inicializa la data de la tabla
    }

    public void initDataTable(){
        tabla = new TabletDynamic(findViewById(R.id.tablet),this);
        header = new String[]{"Id", "Descripcion", "Causa", "Servicio", "Fecha inicial", "Fecha final", "Indisponibilidad"};
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
            System.out.println(mCur.toString());
        }
    }

    public void crearTableLayout(int numeroFilas, int numeroColumnas , Cursor fil){
        tableLayout.removeAllViews();

        for (int i = 0;i <numeroFilas;i++){//8 veces
            TableRow fila = new TableRow(getApplicationContext());//Instanciamos
            //Declaramos atributos de la fila
            TableLayout.LayoutParams lpFila = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);//Parametros del layout para el boton
            lpFila.weight=1;//Peso
            fila.setLayoutParams(lpFila);

            for (int y = 0; y < numeroColumnas; y++){

                    TextView text0 = new TextView(getApplicationContext());//Instanciamos
                    text0.setId(View.generateViewId());
                    text0.setText(fil.getString(y));
                    TableRow.LayoutParams lptext2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);//Parametros del layout para el boton
                    lptext2.weight=1;
                    text0.setLayoutParams(lptext2);//Añadimos los parametros al boton
                    fila.addView(text0);//Añadimos el boton a la fila
            }
            //Agregamos la fila a el tablelayout
            tableLayout.addView(fila);
        }
    }

}
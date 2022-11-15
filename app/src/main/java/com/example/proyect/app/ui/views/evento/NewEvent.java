package com.example.proyect.app.ui.views.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyect.R;
import com.example.proyect.app.ui.Controller.Utiles;
import com.example.proyect.core.DataBase.models.Eventos;
import com.example.proyect.core.DataBase.services.DBManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class NewEvent extends AppCompatActivity {

    private Button guardar;
    private Button   terminar;
    private Button iniciar;

    private TextView initDate;
    private TextView diferences;
    private TextView servicio;
    private TextView descripcions;
    private TextView causa;
    private String endDate;
    Integer newEvtID;
    Eventos  newEvt;
    private boolean isfFinish;
    DBManager base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

         base = new DBManager(getBaseContext());
        guardar = findViewById(R.id.btnGuardar);
        terminar = findViewById(R.id.btnEnd);
        initDate = findViewById(R.id.txtFechaInit);
        diferences = findViewById(R.id.textDiferences);
        causa = findViewById(R.id.textCausa);
        servicio  =findViewById(R.id.textServicio) ;
        descripcions =findViewById(R.id.textDescri);
        isfFinish= false;
        endDate= "";
        newEvtID = 0;
        newEvt = null;

        setDAteInit();//se llena la fecha inicial

        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setDiferences();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   insertar();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
            }
        });
    }

    public void setDAteInit(){
        String  fecha =     Utiles.obtenerFechaActual("GMT-5");
        initDate.setText(fecha);
    }


public void setDiferences() throws ParseException {
    endDate =     Utiles.obtenerFechaActual("GMT-5");


     String diren = Utiles.obtenerDiferences(initDate.getText().toString(), endDate);
     diferences.setText(diren);
    if(diferences.getText() != "se calcula al terminar"){
        isfFinish = true;
    }

}



public boolean insertar() throws SQLException {
        if( !causa.getText().toString().isEmpty()  && !descripcions.getText().toString().isEmpty() && !servicio.getText().toString().isEmpty()){
            if(isfFinish){
                if(newEvtID == 0){
                    newEvt  = new Eventos(initDate.getText().toString(),descripcions.getText().toString(),causa.getText().toString(),servicio.getText().toString(),endDate,diferences.getText().toString());
                    base.open();
                    base.insert(newEvt);
                    base.close();

                    newEvtID = newEvt.getId();
                    isfFinish = false;
                    return  true;
                }else{
                    newEvt.setCausa(causa.getText().toString());
                    newEvt.setDescripción(descripcions.getText().toString());
                    newEvt.setServicioAfectado(servicio.getText().toString());
                    newEvt.setIndisponibildad(diferences.getText().toString());
                    newEvt.setFechaFin(endDate);
                    base.open();
                    base.update(newEvt,newEvtID);//se mpdifica si hay alguno
                    base.close();
                    isfFinish = false;
                    Toast toast = Toast.makeText(this,"Ya se encuentra un Evento regitrado \n Se modifico CODIGO :" +newEvtID, Toast.LENGTH_LONG);
                    toast.show();
                    return  true;
                }
            }else{
                Toast toast = Toast.makeText(this,"Debe terminar el evento", Toast.LENGTH_LONG);
                toast.show();
            }
        }else{
            Toast toast = Toast.makeText(this,"Campos requeridos vacios", Toast.LENGTH_LONG);
            toast.show();
        }
    return  false;
}
}
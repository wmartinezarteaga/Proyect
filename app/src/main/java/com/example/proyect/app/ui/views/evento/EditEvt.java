package com.example.proyect.app.ui.views.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyect.R;
import com.example.proyect.app.ui.Controller.Message;
import com.example.proyect.app.ui.views.MenuEventos;
import com.example.proyect.core.DataBase.models.Eventos;
import com.example.proyect.core.DataBase.services.DBManager;

import java.sql.SQLException;
import java.util.Objects;


public class EditEvt extends AppCompatActivity {
    private Button editar;
    private Button   eliminar;

    private TextView initDate;
    private TextView diferences;
    private TextView servicio;
    private TextView descripcions;
    private TextView causa;
    Integer newEvtID;
    Eventos newEvt;
    DBManager base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_evt);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Editar Evento");
        newEvtID = 0;
        newEvt = null;
        base = new DBManager(getBaseContext());
        editar = findViewById(R.id.btnUpdate);
        eliminar = findViewById(R.id.btndeleteEdit);
        initDate = findViewById(R.id.txtFechaInitEdit);
        diferences = findViewById(R.id.textDiferencesEdit);
        causa = findViewById(R.id.textCausaEdit);
        servicio  =findViewById(R.id.textServicioEdit) ;
        descripcions =findViewById(R.id.textDescriEdit);

       getDataInit();//inicializa la data

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();

            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    update();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void update() throws SQLException {
        if(newEvt != null){

            newEvt.setDescripción(descripcions.getText().toString());
            newEvt.setCausa(causa.getText().toString());
            newEvt.setServicioAfectado(servicio.getText().toString());

            base.open();
           int response = base.update(newEvt,newEvtID);
            base.close();

            if(response >0){
                Message.message(getBaseContext(), "Editado con exito");
                Intent loo = new Intent( EditEvt.this, MenuEventos.class);
                startActivity(loo);
            }else {
                Message.message(getBaseContext(), "Oops, No hay evento que eliminar");
            }
        }
    }


    public void eliminar(){

        if(newEvtID != 0){
            try {
                base.open();
                int response=  base.delete(newEvtID);

                if(response == 1){
                    Message.message(getBaseContext(), "Se elimino el Evento: "+newEvtID);
                   Intent loo = new Intent( EditEvt.this, MenuEventos.class);
                   startActivity(loo);
                }else {
                    Message.message(getBaseContext(), "Oops hubo un error al eliminar : "+newEvtID);
                }
                base.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            Message.message(getBaseContext(), "Oops, No hay evento que eliminar");
        }
    }

    void getDataInit(){
        Intent data = getIntent();

      String[] arrayData = data.getStringArrayExtra("data");  //data.getStringArrayExtra("data");
        String id   =arrayData[0];
        String descrip   =arrayData[1];
        String causa2=arrayData[2];
        String service=arrayData[3];
        String initDate2=arrayData[4];
        String finishDate=arrayData[5];
        String indisponibilidad=arrayData[6];

        newEvt = new Eventos(initDate2,descrip,causa2,service,finishDate,indisponibilidad);

        newEvtID = Integer.valueOf(id);
      diferences.setText(indisponibilidad);
      causa.setText(causa2);
      servicio.setText(service);
      descripcions.setText(descrip);
      initDate.setText(initDate2);
    }

}
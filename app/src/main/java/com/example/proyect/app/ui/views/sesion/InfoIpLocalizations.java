package com.example.proyect.app.ui.views.sesion;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyect.R;
import com.example.proyect.app.ui.controller.Message;
import com.example.proyect.app.ui.controller.ModelViewAdapter;
import com.example.proyect.core.database.models.Localizacion;
import com.example.proyect.core.database.services.DBManager;
import com.example.proyect.core.database.services.NotificationsServices;

import java.sql.SQLException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoIpLocalizations extends AppCompatActivity  {


    private EditText data;
 private Localizacion dataLocalizacion;
 private  DBManager base;
 private String ipSearh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ip_localizations);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Info del Ip");
        base = new DBManager(getBaseContext());
        Button saved = findViewById(R.id.btnLocalSave);
         data = findViewById(R.id.txtInfo);

        Intent ip = getIntent();
        ipSearh = ip.getStringExtra("ip");
      getData();
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new InfoIpLocalizations.TaskInsert().execute("");

            }
        });

    }

    public void getData(){
         Call<Localizacion>  call = ModelViewAdapter.getApiService().getModels(ipSearh);
         call.enqueue(new Callback<Localizacion>() {
             @Override
             public void onResponse(@NonNull Call<Localizacion> call, @NonNull Response<Localizacion> response) {
                 if(response.isSuccessful()){
                      dataLocalizacion = response.body();

                     String content = "";
                     assert dataLocalizacion != null;
                     content = "Ip => "+dataLocalizacion.getQuery()+ "\n"+
                               "Pais => "+dataLocalizacion.getCountry() + "\n"+
                               "Ciudad => "+dataLocalizacion.getCity()+ "\n"+
                               "Region => "+dataLocalizacion.getRegionName()+ "\n"+
                               "Zona => "+dataLocalizacion.getTimezone()+ "\n";
                     data.setText(content);
                 }
                 if(dataLocalizacion.getCountry() == null){
                     NotificationsServices.recordatorio(getBaseContext(),"Oops!!","Ip no encontrada");
                 }else{
                     NotificationsServices.recordatorio(getBaseContext(),"Genial!!"," Datos de la Ip " +ipSearh+" encontrada");
                 }
             }

             @Override
             public void onFailure(Call<Localizacion> call, Throwable t) {

             }
         });
    }


    public boolean insertarPosision() throws SQLException {
        if(!dataLocalizacion.getQuery().isEmpty() ){
                    base.open();
                    base.insertPosision(dataLocalizacion);
                    base.close();
                    return  true;
        }else{
            Message.message(this, "Oops! Campos requeridos vacios");
        }
        return  false;
    }


    class TaskInsert extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(0000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }
        @Override
        protected void onPostExecute(String s) {
            // progress.setVisibility(View.INVISIBLE);
            try {
                insertarPosision();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {
            // progress.setVisibility(View.VISIBLE);
        }
    }
}
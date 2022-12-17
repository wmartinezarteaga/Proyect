package com.example.proyect.app.ui.views.Sesion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyect.R;
import com.example.proyect.app.ui.Controller.ModelViewAdapter;
import com.example.proyect.core.DataBase.models.Localizacion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoIpLocalizations extends AppCompatActivity implements Callback<Localizacion> {


 private Button saved;
 private EditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ip_localizations);

        saved = findViewById(R.id.btnLocalSave);
         data = findViewById(R.id.txtInfo);

        getData();
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    @Override
    public void onResponse(Call<Localizacion> call, Response<Localizacion> response) {
        if(response.isSuccessful()){
        Localizacion   dataLocalizacion = response.body();
            data.setText(String.valueOf(dataLocalizacion.getREGIONNME()));
            System.out.println(response.body());
        }
    }

    @Override
    public void onFailure(Call<Localizacion> call, Throwable t) {

    }
    public void getData(){
         Call<Localizacion>  call = ModelViewAdapter.getApiService().getModels();
         call.enqueue(this);


    }


}
package com.example.proyect.app.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.proyect.R;
import com.example.proyect.app.ui.views.evento.NewEvent;
import com.example.proyect.app.ui.views.evento.ShowEvt;
import com.example.proyect.core.DataBase.services.DBManager;

import java.sql.SQLException;
import java.util.Objects;

public class MenuEventos extends AppCompatActivity {
   Button registro;
   Button showEvt;
    DBManager base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_eventos);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Opciones de eventos");

        registro = findViewById(R.id.registrar);
        showEvt = findViewById(R.id.btnshowEvt);

        base = new DBManager(getBaseContext());
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loo = new Intent( MenuEventos.this, NewEvent.class);
                startActivity(loo);
            }
        });

        showEvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent show = new Intent( MenuEventos.this, ShowEvt.class);
                    startActivity(show);
            }
        });
    }
}
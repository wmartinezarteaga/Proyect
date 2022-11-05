package com.example.proyect.app.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyect.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //boton para conectar la pantalla menu con la pantalla incio
    public void Inicio(View View){
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio);
    }

    //Boton que conecta la pantalla menu con la pantalla Evento
    public void Evento(View View){
        Intent Evento = new Intent(this, MenuEventos.class);
        startActivity(Evento);
    }

    public void Sesion(View View){
       Intent Evento = new Intent(this, ManuSesion.class);
         startActivity(Evento);
    }


    public void Log(View View){
        Intent Evento = new Intent(this, MenuLog.class);
        startActivity(Evento);
    }
}
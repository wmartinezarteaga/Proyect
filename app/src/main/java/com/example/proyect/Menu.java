package com.example.proyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //boton para conectar la pantalla menu con la pantalla incio
    public void Inicio(View View){
        Intent Inicio = new Intent(this, MainActivity.class);
        startActivity(Inicio);
    }

    //Boton que conecta la pantalla menu con la pantalla Evento
    public void Evento(View View){
        Intent Evento = new Intent(this, MenuEventos.class);
        startActivity(Evento);
    }
}
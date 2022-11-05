package com.example.proyect.app.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyect.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //boton para enlasar el inicio con el menu
    public void Men(View View){
        Intent Men = new Intent(this, Menu.class);
        startActivity(Men);
    }
}
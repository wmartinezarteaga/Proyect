package com.example.proyect.app.ui.views.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.proyect.R;

public class ShowEvt extends AppCompatActivity {
  private  TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_evt);


        tableLayout = findViewById(R.id.tablet);
    }




}
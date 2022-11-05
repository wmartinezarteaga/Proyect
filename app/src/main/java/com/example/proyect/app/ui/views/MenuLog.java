package com.example.proyect.app.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.proyect.app.ui.models.Eventos;
import com.example.proyect.R;


import java.util.ArrayList;
import java.util.List;

public class MenuLog extends AppCompatActivity {
    List<String> listado = new ArrayList<String>();
    ArrayList<Eventos> listFile = new ArrayList<Eventos>();
    private ProgressBar progress;
    private RecyclerView recicle;
   // private Adapter adapter;
    private String texto;
    Eventos eventos;
    private boolean tem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_log);
        progress = findViewById(R.id.progressBarLog);
        recicle = findViewById(R.id.recyclerViewLog);


        new MenuLog.Task1().execute(listado.toString().trim());
    }

    class Task1 extends AsyncTask<String ,Void,String> {

        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            progress.setVisibility(View.INVISIBLE);
          // Eventos eventos = new Eventos()
          // Eventos eventos1 = new Eventos();
          // Eventos eventos2 = new Eventos();
          // Eventos eventos3 = new Eventos();
         // listFile.add(eventos);
         // listFile.add(eventos1);
         // listFile.add(eventos2);
         // listFile.add(eventos3);
         //   adapter = new Adapter(getBaseContext(), listFile);
         //   recicle.setAdapter(adapter);
         //   recicle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        }
        @Override
        protected String doInBackground(String... strings) {


            try{

                Thread.sleep(2000);

            }catch (InterruptedException e){

                e.printStackTrace();


            }

            return strings[0];
        }
    }
}
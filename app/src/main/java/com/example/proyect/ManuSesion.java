package com.example.proyect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyect.data.Adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.Sesions;

public class ManuSesion extends AppCompatActivity {
    List<String> listado = new ArrayList<String>();
    ArrayList<models.Sesions> listado2 = new ArrayList<Sesions>();
    private ProgressBar progress;
    private RecyclerView recicle;
    private Adapter adapter;
    private String texto;
    Sesions sesions;
    private boolean tem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_sesion);

        progress = findViewById(R.id.progressBar);
        recicle = findViewById(R.id.recyclerView);
        new Task1().execute(listado.toString().trim());
    }

    public void searhFileAccedDefined(){
        texto = "";
        listado.clear();
        try {
            InputStream file = this.getResources().openRawResource(R.raw.salidalog);
            InputStreamReader fileLoad = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(fileLoad);
            String linea = br.readLine();
            String todo = "";

            while (linea != null) {
                todo = todo + linea + "\n";
                listado.add(linea.toString().split("] ")[0]);
                listado.add(linea.toString().split("] ")[1]);
                listado.add(linea.toString().split("] ")[2]);
                listado.add(linea.toString().split("] ")[3]);
                listado.add(linea.toString().split("] ")[4]);
                sesions = new Sesions(listado.get(0).toString(), listado.get(1).toString(), listado.get(2).toString(), listado.get(3).toString(), listado.get(4).toString());
                linea = br.readLine();
                listado2.add(sesions);
            }


            br.close();
            fileLoad.close();
            texto = todo;





        } catch (IOException e) {
            Toast toast = Toast.makeText(this.getBaseContext(),e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }



    }
    class Task1 extends AsyncTask<String ,Void,String> {

        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            progress.setVisibility(View.INVISIBLE);
           // searhFileAccedDefined();
            Sesions  sesions = new Sesions("SERVIDOR \nunicor","fecha : 2022/09/09","datos relacionados","Test","EVENTO \nfailed");
            Sesions  sesions1 = new Sesions("SERVIDOR \nMovistar","fecha : 2022/09/09","datos relacionados","Test","EVENTO \nsuccessful");
            Sesions  sesions2 = new Sesions("SERVIDOR \nClaro","fecha : 2022/09/09","datos relacionados","Test","EVENTO \nsuccessful");
            Sesions   sesions3 = new Sesions("SERVIDOR \nTigo","fecha : 2022/09/09","datos relacionados","Test","EVENTO \nfailed");
            listado2.add(sesions);
            listado2.add(sesions1);
            listado2.add(sesions2);
            listado2.add(sesions3);
            adapter = new Adapter(getBaseContext(), listado2);
            recicle.setAdapter(adapter);
            recicle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

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



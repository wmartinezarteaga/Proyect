package com.example.proyect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyect.Controller.Adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Sesions;

public class ManuSesion extends AppCompatActivity {
    List<String> listadoFile = new ArrayList<String>();
    List<String> listIps = new ArrayList<String>();
    List<String> listResponse = new ArrayList<String>();
    List<String> listIpToset = new ArrayList<String>();
    List<String> listSearhData = new ArrayList<String>();
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
        recicle = findViewById(R.id.recyclerViewSesion);
        new Task1().execute(listadoFile.toString().trim());

    }

    public void  searhDataSpecify(String ip){
        listSearhData.clear();
        for (int i = 0; i < listadoFile.toArray().length; i++) {
            if(listadoFile.get(i).contains(ip)){
                listSearhData.add(listadoFile.get(i));
            }
        }
        System.out.println("Para el set de ip "+ip );
        System.out.println("TOTAL RESPUESTAS "+listSearhData.toArray().length );
    }

  public void   initReciclecVIew(){
        if(listIpToset.toArray().length >0) {
            for (int i = 0; i < listIpToset.toArray().length; i++) {
                Sesions  sesions = new Sesions("IP DEL LOG \n",listIpToset.get(i),"","Cantidad  " +count(listIpToset.get(i)),"");
                listado2.add(sesions);
            }
        }

      adapter = new Adapter(getBaseContext(), listado2);
      recicle.setAdapter(adapter);
      recicle.setHasFixedSize(true);
      recicle.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    public int count(String ipSearh){
        int count =0;
        for (int i = 0; i < listIps.toArray().length; i++) {
            if (listIps.get(i).contains(ipSearh)) {
                count++;
            }
        }
        return count;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addAllRequiredData(){
        if(listadoFile.toArray().length >0) {
            for (int i = 0; i < listadoFile.toArray().length; i++) {

                String linea = listadoFile.get(i);// linea ya cargada del archivo

                String[] temData = linea.split(" - - "); // dividir linea

                if(temData != null){
                    listIps.add(temData[0]);// add ip
                    listResponse.add(temData[1]);// add response
                }
            } ;

            // seteo de la lista para solo los ip en especifico
            listIpToset.addAll(listIps.stream().collect(Collectors.toSet()));

            System.out.println("Total de lineas" + listadoFile.toArray().length);
            System.out.println("Total de ip " + listIps.toArray().length);
            System.out.println("Total de toset ip " + listIpToset.toArray().length);
            System.out.println("Total de Response" + listResponse.toArray().length);

        }
    }

    public void searhFileAccedDefined(){
        listadoFile.clear();
        try {
            InputStream file = this.getResources().openRawResource(R.raw.seguridad);
            InputStreamReader fileLoad = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(fileLoad);
            String linea = br.readLine();
            String todo = "";

            while (linea != null) {
                todo = todo + linea + "\n";
                listadoFile.add(linea.toString().split(" 2")[0]);
                linea = br.readLine();
            }

            br.close();
            fileLoad.close();
            Toast toast = Toast.makeText(this.getBaseContext(),"Datos cargados exitosamente!!", Toast.LENGTH_LONG);
            toast.show();
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

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s) {
            progress.setVisibility(View.INVISIBLE);
            searhFileAccedDefined();//carga la data del seguridadLog.txt
            addAllRequiredData();//carga los arreglos con la dta
            initReciclecVIew();//inicializa los reciclew view
            for (int i=0;i<listIpToset.toArray().length; i++){
                new Task2().execute(String.valueOf(i));
            }

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

    class Task2 extends AsyncTask<String ,Void, Integer> {

        @Override
        protected void onPreExecute() {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Integer i) {searhDataSpecify(listIpToset.get(i));}

        @Override
        protected Integer doInBackground(String... strings) {
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return Integer.valueOf(strings[0]);
        }
    }
}





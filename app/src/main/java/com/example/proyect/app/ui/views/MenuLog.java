package com.example.proyect.app.ui.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyect.app.ui.Controller.AdapterGeneralInformation;
import com.example.proyect.core.DataBase.models.Logs;
import com.example.proyect.R;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuLog extends AppCompatActivity {
    List<String> listadoFile = new ArrayList<String>();
    List<String> listIps = new ArrayList<String>();
    List<String> listResponse = new ArrayList<String>();
    List<String> listDate = new ArrayList<String>();
    List<String> listSerial = new ArrayList<String>();
    ArrayList<Logs> listSearhData = new ArrayList<Logs>();
    ArrayList<Logs> listCardLog = new ArrayList<Logs>();
    private ProgressBar progress;
    private RecyclerView recicle;
    private AdapterGeneralInformation adapter;
    private String texto;
    private EditText textBusqueda;
    private Button buscar;
    private boolean tem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_log);
        progress = findViewById(R.id.progressBarseguridad);
        recicle = findViewById(R.id.recyclerViewLog);
        textBusqueda = findViewById(R.id.editSearh);
        buscar = findViewById(R.id.btnBuscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             initTaskSearh();
            }
        });

        new MenuLog.Task1().execute(listadoFile.toString().trim());
    }



    public void  searhDataSpecify(){


        if(textBusqueda.length() >0 ) {
            listSearhData.clear();
            for (int i = 0; i < listCardLog.toArray().length; i++) {
                if (listCardLog.get(i).getNumCard().equals(textBusqueda.getText().toString().toLowerCase())) {
                    listSearhData.add(listCardLog.get(i));
                }
            }
            if(!listSearhData.isEmpty()) {
                adapter = new AdapterGeneralInformation(getBaseContext(), listSearhData);
                recicle.setAdapter(adapter);
                recicle.setHasFixedSize(true);
                recicle.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                System.out.println("Para el set de ip " + textBusqueda);
                System.out.println("TOTAL RESPUESTAS " + listSearhData.toArray().length);
            }else{
                initReciclecVIew();

                Toast toast = Toast.makeText(this.getBaseContext(),"No se encontro registro", Toast.LENGTH_LONG);
                toast.show();

            }
        }else{
            initReciclecVIew();
            Toast toast = Toast.makeText(this.getBaseContext(),"Digite una palabra clave", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void   initReciclecVIew(){
        adapter = new AdapterGeneralInformation(getBaseContext(), listCardLog);
        recicle.setAdapter(adapter);
        recicle.setHasFixedSize(true);
        recicle.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    public void loadCardData(){
    if(listadoFile.toArray().length >0) {
        for (int i = 0; i < listadoFile.toArray().length; i++) {
            Logs logs = new Logs(listIps.get(i),listDate.get(i),listResponse.get(i),listSerial.get(i),"_"+(1+i));

          listCardLog.add(logs);
        }
    }
    }

    void addDateAndResetResponse(){
        if(listResponse.toArray().length >0) {
            for (int i = 0; i < listResponse.toArray().length; i++) {

                String linea = listResponse.get(i);// linea ya cargada del archivo
                String[] temData = linea.split(" \"");

                if(temData != null){
                    listDate.add(temData[0]);// add data
                    listResponse.set(i, temData[1]);// add response
                }

            }
        }
    }

    void addSerialAndResetResponse(){
        if(listResponse.toArray().length >0) {
            for (int i = 0; i < listResponse.toArray().length; i++) {

                String linea = listResponse.get(i);// linea ya cargada del archivo
                String[] temData = linea.split("1\" ");

                if(temData != null){
                    listResponse.set(i, temData[0]);// add response
                    listSerial.add(temData[1]);
                }
            }
        }
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
                listadoFile.add(linea.toString());
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


    public void initTaskSearh(){
        new MenuLog.TaskSearhData().execute(listCardLog.toString().trim());
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
            searhFileAccedDefined();
            addAllRequiredData();//carga los arreglos con la dta
            addDateAndResetResponse();//scrapea la fecha  e ajusta el response
            addSerialAndResetResponse();//scrapea el serial e ajusta el response
            loadCardData();//cargar la info del card
            initReciclecVIew();//inicializa los reciclew view
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


    class TaskSearhData extends AsyncTask<String ,Void, String> {

        @Override
        protected void onPreExecute() {
             progress.setVisibility(View.VISIBLE);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String i) {
            progress.setVisibility(View.INVISIBLE);
            searhDataSpecify();
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
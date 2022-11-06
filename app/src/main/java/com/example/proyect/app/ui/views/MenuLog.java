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
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyect.app.ui.Controller.AdapterGeneralInformation;
import com.example.proyect.app.ui.models.Logs;
import com.example.proyect.R;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MenuLog extends AppCompatActivity {
    List<String> listadoFile = new ArrayList<String>();
    List<String> listIps = new ArrayList<String>();
    List<String> listResponse = new ArrayList<String>();
    List<String> listIpToset = new ArrayList<String>();
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
                if (listCardLog.get(i).getIp().equals(textBusqueda.getText().toString().toLowerCase())) {
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

    public int count(String ipSearh, ArrayList<String> array){
        int count =0;
        for (int i = 0; i < array.toArray().length; i++) {
            if (array.get(i).contains(ipSearh)) {
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

            Logs logs = new Logs("12","23","23","23","2");
            Logs logs2 = new Logs("13","23","23","23","2");
            Logs logs3 = new Logs("14","23","23","2","");
            Logs logs4 = new Logs("15","23","23","23","2");
            listCardLog.add(logs);
            listCardLog.add(logs2);
            listCardLog.add(logs3);
            listCardLog.add(logs4);
            // seteo de la lista para solo los ip en especifico
            listIpToset.addAll(listIps.stream().collect(Collectors.toSet()));

          // System.out.println("Total de lineas" + listadoFile.toArray().length);
          // System.out.println("Total de ip " + listIps.toArray().length);
          // System.out.println("Total de toset ip " + listIpToset.toArray().length);
          // System.out.println("Total de Response" + listResponse.toArray().length);

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
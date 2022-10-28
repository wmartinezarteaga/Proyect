package com.example.proyect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.proyect.Controller.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MenuLog extends AppCompatActivity {
    List<String> listado = new ArrayList<String>();
    ArrayList<models.Sesions> listado2 = new ArrayList<models.Sesions>();
    private ProgressBar progress;
    private RecyclerView recicle;
    private Adapter adapter;
    private String texto;
    models.Sesions sesions;
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
            // searhFileAccedDefined();
            models.Sesions sesions = new models.Sesions("SERVIDOR \nunicor","fecha : 2022/09/09","User: Wilson","Test","EVENTO \nfailed");
            models.Sesions sesions1 = new models.Sesions("SERVIDOR \nMovistar","fecha : 2022/09/09","User: Jose","Test","EVENTO \nsuccessful");
            models.Sesions sesions2 = new models.Sesions("SERVIDOR \nClaro","fecha : 2022/09/09","User: Jose","Test","EVENTO \nsuccessful");
            models.Sesions sesions3 = new models.Sesions("SERVIDOR \nTigo","fecha : 2022/09/09","User: Wilson","Test","EVENTO \nfailed");
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
package com.example.proyect.core.DataBase.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.example.proyect.core.DataBase.models.Localizacion;

import java.util.ArrayList;


public class JobServices extends JobService{
 boolean oncalcel = false;
 ArrayList<Localizacion> arrayList = new ArrayList<>();
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e("","Trabajo inicicado");
        getArrayData(jobParameters);
        return false;
    }

    public void getArrayData(final JobParameters jobParameters){
  new Thread((Runnable) ()-> {

      if (oncalcel) {
          return;
      }

  });
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        oncalcel = true;
        return false;
    }


}


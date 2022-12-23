package com.example.proyect.app.ui.controller;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Utiles {

    public static String obtenerHoraActual(String zonaHoraria) {
        String formato = "HH:mm:ss";
        return Utiles.obtenerFechaConFormato(formato, zonaHoraria);
    }

    public static String obtenerFechaActual(String zonaHoraria) {
        String formato = "dd-MM-yyyy-HH:mm:ss";
        return Utiles.obtenerFechaConFormato(formato, zonaHoraria);
    }
    public static String obtenerDiferences(String initDatel, String endDate) throws ParseException {
        String formato = "dd-MM-yyyy-HH:mm:ss";
        return Utiles.diferencias(formato, initDatel,endDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String obtenerFechaConFormato(String formato, String zonaHoraria) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String diferencias(String formato, String initDatel, String endDate) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

            Date dateStart = dateFormat.parse(initDatel);
            Date dateEnd = dateFormat.parse(endDate);

            long difference = Math.abs(dateEnd.getTime() - dateStart.getTime());

            Log.e("Difference: " ,  Long.toString(difference));

        float hora = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
        float minutos = TimeUnit.MINUTES.convert(difference, TimeUnit.MILLISECONDS);
        float segundos = TimeUnit.SECONDS.convert(difference, TimeUnit.MILLISECONDS);
        return  segundos+"Seg - "+minutos+"Min - "+hora+" Horas";

    }
}

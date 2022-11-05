package com.example.proyect.app.ui.models;

import java.util.Random;

public class Eventos {

    private String fechaIni, descripción, id, causa, servicioAfectado, fechaFin, indisponibildad;

    public Eventos(String fechaIni, String descripción, String causa, String servicioAfectado, String fechaFin, String indisponibildad) {
        this.fechaIni = fechaIni;
        this.descripción = descripción;
        this.id = String.valueOf(new Random().nextInt(200));
        this.causa = causa;
        this.servicioAfectado = servicioAfectado;
        this.fechaFin = fechaFin;
        this.indisponibildad = indisponibildad;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getServicioAfectado() {
        return servicioAfectado;
    }

    public void setServicioAfectado(String servicioAfectado) {
        this.servicioAfectado = servicioAfectado;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIndisponibildad() {
        return indisponibildad;
    }

    public void setIndisponibildad(String indisponibildad) {
        this.indisponibildad = indisponibildad;
    }
}
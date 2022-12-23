package com.example.proyect.core.database.models;

public class Logs {

    String ip, date, response, serial,numCard;


    public Logs(String ip, String date, String response, String serial, String nim) {
        this.ip = ip;
        this.date = date;
        this.response = response;
        this.serial = serial;
        this.numCard = nim;
    }

    public String getIp() {
        return ip;
    }


    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}

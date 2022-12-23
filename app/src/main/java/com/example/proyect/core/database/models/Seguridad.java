package com.example.proyect.core.database.models;

public class Seguridad {

    String ip, nameCard, quantity;

    public Seguridad(String ip, String nameCard, String quantity) {
        this.ip = ip;
        this.nameCard = nameCard;
        this.quantity = quantity;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

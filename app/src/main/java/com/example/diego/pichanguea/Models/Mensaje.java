package com.example.diego.pichanguea.Models;

/**
 * Created by diego on 16-11-2017.
 */

public class Mensaje {
    String idMensaje;
    String jugUsername;
    String contenidoMensaje;

    public Mensaje(){
        ;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getJugUsername() {
        return jugUsername;
    }

    public void setJugUsername(String jugUsername) {
        this.jugUsername = jugUsername;
    }

    public String getContenidoMensaje() {
        return contenidoMensaje;
    }

    public void setContenidoMensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }
}


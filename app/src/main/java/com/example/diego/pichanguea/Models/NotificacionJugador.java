package com.example.diego.pichanguea.Models;

/**
 * Created by diego on 29-11-2017.
 */

public class NotificacionJugador {
    String idJugador;
    String idPartido;
    String titulo;
    String texto;
    String data;
    String topic;



    public NotificacionJugador(String idJugador, String idPartido, String titulo, String texto, String data,String topic) {
        this.idJugador = idJugador;
        this.idPartido = idPartido;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.topic = topic;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

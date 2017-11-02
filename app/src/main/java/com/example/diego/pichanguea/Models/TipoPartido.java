package com.example.diego.pichanguea.Models;

/**
 * Created by Diego on 02-11-2017.
 */

public class TipoPartido {
    String idTipoPartido;
    String idDeporte;
    String tpaNombre;
    int tpaMaximoJugadores;

    public TipoPartido() {
        this.idTipoPartido = idTipoPartido;
        this.idDeporte = idDeporte;
        this.tpaNombre = tpaNombre;
        this.tpaMaximoJugadores = tpaMaximoJugadores;
    }

    public String getIdTipoPartido() {
        return idTipoPartido;
    }

    public void setIdTipoPartido(String idTipoPartido) {
        this.idTipoPartido = idTipoPartido;
    }

    public String getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(String idDeporte) {
        this.idDeporte = idDeporte;
    }

    public String getTpaNombre() {
        return tpaNombre;
    }

    public void setTpaNombre(String tpaNombre) {
        this.tpaNombre = tpaNombre;
    }

    public int getTpaMaximoJugadores() {
        return tpaMaximoJugadores;
    }

    public void setTpaMaximoJugadores(int tpaMaximoJugadores) {
        this.tpaMaximoJugadores = tpaMaximoJugadores;
    }
}


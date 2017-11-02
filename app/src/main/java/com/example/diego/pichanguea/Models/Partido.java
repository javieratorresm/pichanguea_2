package com.example.diego.pichanguea.Models;

/**
 * Created by Diego on 02-11-2017.
 */

public class Partido {
    String idPartido;
    Equipo equipo;
    TipoPartido tipoPartido;
    String parCancha;
    String parComplejo;
    String parCreacion;
    String parEstado;
    String parFecha;
    String parGeoReferencia;
    String parHora;
    String parRival;
    String parUbicacion;

    public Partido() {
        this.idPartido = idPartido;
        this.equipo = equipo;
        this.tipoPartido = tipoPartido;
        this.parCancha = parCancha;
        this.parComplejo = parComplejo;
        this.parCreacion = parCreacion;
        this.parEstado = parEstado;
        this.parFecha = parFecha;
        this.parGeoReferencia = parGeoReferencia;
        this.parHora = parHora;
        this.parRival = parRival;
        this.parUbicacion = parUbicacion;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public TipoPartido getTipoPartido() {
        return tipoPartido;
    }

    public void setTipoPartido(TipoPartido tipoPartido) {
        this.tipoPartido = tipoPartido;
    }

    public String getParCancha() {
        return parCancha;
    }

    public void setParCancha(String parCancha) {
        this.parCancha = parCancha;
    }

    public String getParComplejo() {
        return parComplejo;
    }

    public void setParComplejo(String parComplejo) {
        this.parComplejo = parComplejo;
    }

    public String getParCreacion() {
        return parCreacion;
    }

    public void setParCreacion(String parCreacion) {
        this.parCreacion = parCreacion;
    }

    public String getParEstado() {
        return parEstado;
    }

    public void setParEstado(String parEstado) {
        this.parEstado = parEstado;
    }

    public String getParFecha() {
        return parFecha;
    }

    public void setParFecha(String parFecha) {
        this.parFecha = parFecha;
    }

    public String getParGeoReferencia() {
        return parGeoReferencia;
    }

    public void setParGeoReferencia(String parGeoReferencia) {
        this.parGeoReferencia = parGeoReferencia;
    }

    public String getParHora() {
        return parHora;
    }

    public void setParHora(String parHora) {
        this.parHora = parHora;
    }

    public String getParRival() {
        return parRival;
    }

    public void setParRival(String parRival) {
        this.parRival = parRival;
    }

    public String getParUbicacion() {
        return parUbicacion;
    }

    public void setParUbicacion(String parUbicacion) {
        this.parUbicacion = parUbicacion;
    }
}

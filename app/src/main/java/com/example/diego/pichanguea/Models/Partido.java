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
    String parDia;
    String parMes;
    String parAno;
    String parGeoReferencia;
    String parHora;
    String parMinutos;
    String parHoras;
    String parRival;
    String parUbicacion;
    int numeroGalletas;
    String asistencia;
    int galletasCarga;
    int cantidadJugadoresMasGalletas;



    public Partido() {

    }

    public int getCantidadJugadoresMasGalletas() {
        return cantidadJugadoresMasGalletas;
    }

    public void setCantidadJugadoresMasGalletas(int cantidadJugadoresMasGalletas) {
        this.cantidadJugadoresMasGalletas = cantidadJugadoresMasGalletas;
    }

    public int getGalletasCarga() {
        return galletasCarga;
    }

    public void setGalletasCarga(int galletasCarga) {
        this.galletasCarga = galletasCarga;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getParDia() {
        return parDia;
    }

    public void setParDia(String parDia) {
        this.parDia = parDia;
    }

    public String getParMes() {
        return parMes;
    }

    public void setParMes(String parMes) {
        this.parMes = parMes;
    }

    public String getParAno() {
        return parAno;
    }

    public void setParAno(String parAno) {
        this.parAno = parAno;
    }

    public String getParMinutos() {
        return parMinutos;
    }

    public void setParMinutos(String parMinutos) {
        this.parMinutos = parMinutos;
    }

    public String getParHoras() {
        return parHoras;
    }

    public void setParHoras(String parHoras) {
        this.parHoras = parHoras;
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

    public int getNumeroGalletas() {
        return numeroGalletas;
    }

    public void setNumeroGalletas(int numeroGalletas) {
        this.numeroGalletas = numeroGalletas;
    }
}

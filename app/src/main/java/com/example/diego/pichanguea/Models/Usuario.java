package com.example.diego.pichanguea.Models;

/**
 * Created by diego on 05-10-2017.
 */

public class Usuario {
    String nombreUsuario;
    String clave;
    String rutSinDigito;
    String rutConDigito;
    String nombre;
    String paterno;
    String materno;
    String celular;
    String mail;
    String apodo;
    String id;
    String token;

    public Usuario() {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.rutSinDigito = rutSinDigito;
        this.rutConDigito = rutConDigito;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.celular = celular;
        this.mail = mail;
        this.apodo = apodo;
        this.id = id;
        this.token = token;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRutSinDigito() {
        return rutSinDigito;
    }

    public void setRutSinDigito(String rutSinDigito) {
        this.rutSinDigito = rutSinDigito;
    }

    public String getRutConDigito() {
        return rutConDigito;
    }

    public void setRutConDigito(String rutConDigito) {
        this.rutConDigito = rutConDigito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
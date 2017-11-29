package com.example.diego.pichanguea.Classes;

import android.app.Application;
import android.content.Context;

import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.Usuario;

/**
 * Created by diego on 29-11-2017.
 */

public class Singleton {
    Usuario usuario;
    Partido partido;
    String datosPartidos;
    String token="0";
    private static Singleton instance = null;



    //a private constructor so no instances can be made outside this class
    private Singleton() {}

    //Everytime you need an instance, call this
    //synchronized to make the call thread-safe
    public static synchronized Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();

        return instance;
    }

    //Initialize this or any other variables in probably the Application class
    public void init(Context context) {}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public String getDatosPartidos() {
        return datosPartidos;
    }

    public void setDatosPartidos(String datosPartidos) {
        this.datosPartidos = datosPartidos;
    }

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

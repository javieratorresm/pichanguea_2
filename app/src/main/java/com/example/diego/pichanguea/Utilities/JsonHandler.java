package com.example.diego.pichanguea.Utilities;

import android.util.Log;

import com.example.diego.pichanguea.Models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonHandler {

    public Usuario getInformacion(String datos, Usuario usuario){
        try{
            JSONObject jo = new JSONObject(datos);

            usuario.setNombreUsuario(jo.getJSONObject("jugador").getString("jugUsername"));
            usuario.setClave(jo.getJSONObject("jugador").getString("jugPassword"));
            usuario.setRutSinDigito(jo.getJSONObject("jugador").getString("jugRut"));
            usuario.setRutConDigito(jo.getJSONObject("jugador").getString("jugRutDv"));
            usuario.setNombre(jo.getJSONObject("jugador").getString("jugNombre"));
            usuario.setPaterno(jo.getJSONObject("jugador").getString("jugPaterno"));
            usuario.setMaterno(jo.getJSONObject("jugador").getString("jugMaterno"));
            usuario.setCelular(jo.getJSONObject("jugador").getString("jugFono"));
            usuario.setMail(jo.getJSONObject("jugador").getString("jugEmail"));
            usuario.setApodo(jo.getJSONObject("jugador").getString("jugApodo"));
            usuario.setId(jo.getJSONObject("jugador").getString("idJugador"));
            usuario.setToken(jo.getString("token"));


            return usuario;
        }
        catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        //return null;
        return usuario;
    }

    public String[] getPartidos(String datos){
        try {
            JSONArray ja = new JSONArray(datos);
            String[] result = new String[10];
            String actor;
            for (int i = 0; i < 10; i++) {
                JSONObject row = ja.getJSONObject(i);
                actor =row.getJSONObject("partido").getJSONObject("equipo").getString("equNombre")+"            "+row.getJSONObject("partido").getJSONObject("tipoPartido").getString("tpaNombre");
                result[i] = actor;

            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;

    }


    public JSONObject setRegister(Usuario usuario) {
        // build jsonObject
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("nombreUsuario", usuario.getNombreUsuario());
            jsonObject.accumulate("clave", usuario.getClave());
            jsonObject.accumulate("rutSinDigito", usuario.getRutSinDigito());
            jsonObject.accumulate("rutConDigito", usuario.getRutConDigito());
            jsonObject.accumulate("nombre", usuario.getNombre());
            jsonObject.accumulate("paterno", usuario.getPaterno());
            jsonObject.accumulate("materno", usuario.getMaterno());
            jsonObject.accumulate("celular", usuario.getCelular());
            jsonObject.accumulate("mail", usuario.getMail());
            jsonObject.accumulate("apodo", usuario.getApodo());
            return jsonObject;

        } catch (JSONException je) {
            Log.e("ERROR", this.getClass().toString() + " - " + je.getMessage());
        }
        return null;
    }


    /**
     * Método que recibe un JSONArray en forma de String y devuelve un String[] con los actores

     public String[] getPublicacion(String publicaciones){
     try {
     JSONArray ja = new JSONArray(publicaciones);
     String[] result = new String[ja.length()];
     String actor;
     for (int i = 0; i < ja.length(); i++) {
     JSONObject row = ja.getJSONObject(i);
     actor =row.getJSONObject("usuario").getString("nombreusuario")+" "+row.getJSONObject("usuario").getString("apellidousuario")
     +" publicó:\n"+row.getString("nombrepublicacion");
     result[i] = actor;
     }
     return result;
     } catch (JSONException e) {
     Log.e("ERROR", this.getClass().toString() + " " + e.toString());
     }
     return null;

     }

    public String[] getUsuario(String usuario) {
        try {
            JSONArray ja = new JSONArray(usuario);
            String[] result = new String[ja.length()];
            String actor;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor = " " + row.getString("nombre") + " " + row.getString("apellido");
                result[i] = actor;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// getActors(String actors)

    public JSONObject setUsuario(Usuario usuario) {
        // build jsonObject
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("apellidousuario", usuario.getAPELLIDOUSUARIO());
            jsonObject.accumulate("celularusuario", usuario.getCELULARUSUARIO());
            jsonObject.accumulate("emailusuario", usuario.getEMAILUSUARIO());
            jsonObject.accumulate("nicknameusuario", usuario.getNICKNAMEUSUARIO());
            jsonObject.accumulate("nombreusuario", usuario.getNOMBREUSUARIO());
            jsonObject.accumulate("passwordusuario", usuario.getPASSWORDUSUARIO());
            return jsonObject;

        } catch (JSONException je) {
            Log.e("ERROR", this.getClass().toString() + " - " + je.getMessage());
        }
        return null;
    }



*/
}// JsonHandler
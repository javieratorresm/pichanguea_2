package com.example.diego.pichanguea.Utilities;

import android.util.Log;

import com.example.diego.pichanguea.Models.Equipo;
import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.TipoPartido;
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
            usuario.setId(String.valueOf(Math.round(Float.valueOf(jo.getJSONObject("jugador").getString("idJugador")))));
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
            String[] result = new String[ja.length()];
            String actor;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor =row.getJSONObject("partido").getJSONObject("equipo").getString("equNombre")+"@#"+row.getJSONObject("partido").getJSONObject("tipoPartido").getString("tpaNombre")+"@#"+row.getJSONObject("partido").getJSONObject("parFecha").getString("Dia")+"/"+row.getJSONObject("partido").getJSONObject("parFecha").getString("Mes")+"/"+row.getJSONObject("partido").getJSONObject("parFecha").getString("Año")+"@#"+row.getString("asistencia")+"@#"+row.getJSONObject("partido").getString("idPartido");
                result[i] = actor;


            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;

    }
    public String[] getChat(String datos){
        try { JSONArray ja = new JSONArray(datos);
            String[] result = new String[ja.length()];
            String actor;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor =row.getJSONObject("autor").getString("jugUsername")+"@#"+row.getString("contenidoMensaje");
                result[i] = actor;
                System.out.println(actor);

            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }

        return null;
    }
    public String[] getJugadores(String datos){
        try {
            JSONArray ja = new JSONArray(datos);


            String actor;
            int c=0;
            for(int j=0;j < ja.length(); j++){
                JSONObject row = ja.getJSONObject(j);
                if(Math.round(Float.valueOf(row.getString("asistencia")))==1){
                    c+=1;
                }



            }
            String[] result = new String[c];
            c=0;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor=row.getJSONObject("jugador").getString("jugNombre")+" "+row.getJSONObject("jugador").getString("jugPaterno")+"@#"+row.getString("galletas");
                System.out.println(c);
                if(Math.round(Float.valueOf(row.getString("asistencia")))==1){
                    result[c] = actor;
                    c+=1;

                }
            }
            System.out.println("datos");
            System.out.println(c);
            System.out.println(ja.length());
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;

    }
    public void getPartido(String datos, int posicion, Partido partido, TipoPartido tipoPartido,Equipo equipo){
        try {
            JSONArray ja = new JSONArray(datos);

            JSONObject row = ja.getJSONObject(posicion);
            equipo.setEquNombre(row.getJSONObject("partido").getJSONObject("equipo").getString("equNombre"));
            partido.setIdPartido(row.getJSONObject("partido").getString("idPartido"));
            tipoPartido.setIdTipoPartido(row.getJSONObject("partido").getJSONObject("tipoPartido").getString("idTipoPartido"));
            tipoPartido.setIdDeporte(row.getJSONObject("partido").getJSONObject("tipoPartido").getString("idDeporte"));
            tipoPartido.setTpaNombre(row.getJSONObject("partido").getJSONObject("tipoPartido").getString("tpaNombre"));
            tipoPartido.setTpaMaximoJugadores(Math.round(Float.valueOf(row.getJSONObject("partido").getJSONObject("tipoPartido").getString("tpaMaximoJugadores"))));

            partido.setTipoPartido(tipoPartido);
            partido.setIdPartido(String.valueOf(Math.round(Float.valueOf(row.getJSONObject("partido").getString("idPartido")))));
            partido.setParFecha(row.getJSONObject("partido").getString("parFecha"));
            partido.setParDia(row.getJSONObject("partido").getJSONObject("parFecha").getString("Dia"));
            partido.setParMes(row.getJSONObject("partido").getJSONObject("parFecha").getString("Mes"));
            partido.setParAno(row.getJSONObject("partido").getJSONObject("parFecha").getString("Año"));
            partido.setAsistencia(row.getString("asistencia"));
            partido.setGalletasCarga(Math.round(Float.valueOf(row.getString("galletas"))));

            partido.setParHora(row.getJSONObject("partido").getString("parHora"));

            if(Integer.valueOf(row.getJSONObject("partido").getJSONObject("parHora").getString("Hora"))<10){
                partido.setParHoras("0"+row.getJSONObject("partido").getJSONObject("parHora").getString("Hora"));            }
            else{
                partido.setParHoras(row.getJSONObject("partido").getJSONObject("parHora").getString("Hora"));
            }


            if(Integer.valueOf(row.getJSONObject("partido").getJSONObject("parHora").getString("Minutos"))<10){
                partido.setParMinutos("0"+row.getJSONObject("partido").getJSONObject("parHora").getString("Minutos"));
            }
            else{
                partido.setParMinutos(row.getJSONObject("partido").getJSONObject("parHora").getString("Minutos"));

            }
            partido.setParComplejo(row.getJSONObject("partido").getString("parComplejo"));
            partido.setParCancha(row.getJSONObject("partido").getString("parCancha"));
            partido.setEquipo(equipo);



        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }

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
package com.example.diego.pichanguea.Controllers.Controllers.Put;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.diego.pichanguea.Activities.InfoPartidoActivity;
import com.example.diego.pichanguea.Utilities.SSLTrust;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Diego on 18-11-2017.
 */

public class ModificarGalletasPut extends AsyncTask<String, Void, String> {
    private SSLTrust sT;
    private InfoPartidoActivity infoPartidoActivity;
    Toast toast;
    protected String doInBackground(String... parametros) {

        try {
            URL u = new URL(parametros[0]);
            //sT.trustEveryone(); //necesario para conexión ssl
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setFixedLengthStreamingMode(parametros[1].getBytes().length);
            connection.connect();
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            os.write(parametros[1].getBytes());
            os.flush();
            return "OK";
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return "ERROR";
    }
    @Override
    protected void onPostExecute(String result) {


    }
}

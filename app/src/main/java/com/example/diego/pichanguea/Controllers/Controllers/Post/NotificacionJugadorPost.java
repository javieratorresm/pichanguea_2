package com.example.diego.pichanguea.Controllers.Controllers.Post;

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
 * Created by diego on 29-11-2017.
 */

public class NotificacionJugadorPost extends AsyncTask<String, Void, String> {
    private SSLTrust sT;
    private InfoPartidoActivity infoPartidoActivity;
    Toast toast;
    private String key="key=AAAAJmQ3UD4:APA91bHc7DrJOyOKtJYkTFVMiEIu2greybadtDBRDZSp8_Sq_QMQU1b8NYZlyRrXAIsIZ5wNVeZzvbtHrjC0tLaB3AX2I0j0Sdgc2a9P-VlSNV5aPatYQf1cIj-60-XDvq_HjdClPCGW";


    @Override
    protected String doInBackground(String... parametros) {


        try {
            URL u = new URL(parametros[0]);
            //sT.trustEveryone(); //necesario para conexión ssl
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            System.out.println(key);
            connection.setRequestProperty ("Authorization", key);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setFixedLengthStreamingMode(parametros[1].getBytes().length);
            connection.connect();
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            os.write(parametros[1].getBytes());
            os.flush();
            return os.toString();
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return "ERROR";
    }
    @Override
    protected void onPostExecute(String result) {

    }

}
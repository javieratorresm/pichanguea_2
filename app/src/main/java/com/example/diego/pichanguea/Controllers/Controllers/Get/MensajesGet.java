package com.example.diego.pichanguea.Controllers.Controllers.Get;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.diego.pichanguea.Activities.ChatActivity;
import com.example.diego.pichanguea.Utilities.SSLTrust;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by diego on 05-10-2017.
 */

public class MensajesGet extends AsyncTask<String, Void, String> {
    private SSLTrust sT;
    private ChatActivity chatActivity;
    Toast toast;
    public MensajesGet(ChatActivity chatActivity){
        this.chatActivity=chatActivity;
    }
    @Override
    protected String doInBackground(String...url) {
        try {
            URL u = new URL(url[0]);
            //sT.trustEveryone(); //necesario para conexión ssl
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (MalformedURLException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (ProtocolException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// doInBackground(String... urls)
    protected void onPostExecute(String result) {
        chatActivity.mostrarMensajes(result);
    }
}

package com.example.diego.pichanguea.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diego.pichanguea.Classes.AdapterChat;
import com.example.diego.pichanguea.Controllers.Get.Get.jugadoresGet;
import com.example.diego.pichanguea.Controllers.Get.Get.mensajesGet;
import com.example.diego.pichanguea.Controllers.Get.Post.confirmarPost;
import com.example.diego.pichanguea.Controllers.Get.Post.enviarMensajePost;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;

public class ChatActivity extends AppCompatActivity {
    private AdapterChat adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat del partido");
        new mensajesGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/219/chat");


    }

    public void mostrarMensajes(String result) {
        System.out.println(result);
        JsonHandler jh=new JsonHandler();
        String[] listaMensajes=jh.getChat(result);
        ListView mensajesListView=(ListView)findViewById(R.id.listMensajes);

        adapter=new AdapterChat(this,listaMensajes);
        mensajesListView.setAdapter(adapter);

    }

    public void enviarMensaje(View view) {
        new enviarMensajePost().execute(getResources().getString(R.string.servidor) + "api/Jugador/10016/Partidos/219/Chat", "{contenidoMensaje: sample string 1}");

    }

    /*public void mostrarMensajes(String result) {
        System.out.println("asdasd");
    }*/
}

package com.example.diego.pichanguea.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diego.pichanguea.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat del partido");

        String[] values = new String[] {
                "Pedro: Cuando es?",
                "Juan:Que hay que llevar?",
                "Diego:mensaje de prueba",
                "Diego:otro mensaje de prueba",
                "Claudio: no podré ir",
                ""
        };
        ListView listaMensajes=(ListView)findViewById(R.id.listMensajes);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.elemento_mensaje,R.id.nombreMensaje,values);

        listaMensajes.setAdapter(adapter);


    }

    public void mostrarMensajes(String result) {
    }

    /*public void mostrarMensajes(String result) {
        System.out.println("asdasd");
    }*/
}

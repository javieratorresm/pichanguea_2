package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.AdapterChat;
import com.example.diego.pichanguea.Controllers.Get.Get.jugadoresGet;
import com.example.diego.pichanguea.Controllers.Get.Get.mensajesGet;
import com.example.diego.pichanguea.Controllers.Get.Post.confirmarPost;
import com.example.diego.pichanguea.Controllers.Get.Post.enviarMensajePost;
import com.example.diego.pichanguea.Models.Mensaje;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;

public class ChatActivity extends AppCompatActivity {
    private AdapterChat adapter;
    public Mensaje mensaje=new Mensaje();
    EditText textoEditMensaje;
    Toast toast;
    private String idUsuario;
    private String idPartido;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat del partido");
        idUsuario = getIntent().getExtras().getString("idUsuario");
        idPartido= getIntent().getExtras().getString("idPartido");
        new mensajesGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+idPartido+"/chat");


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
        JsonHandler jh=new JsonHandler();
        textoEditMensaje=(EditText)findViewById(R.id.editMensaje);
        String textoMensaje= textoEditMensaje.getText().toString();
        System.out.println(textoMensaje);
        if (textoMensaje!=null){
            new enviarMensajePost(this).execute(getResources().getString(R.string.servidor) + "api/Jugador/"+idUsuario+"/Partidos/"+idPartido+"/Chat", "{\"contenidoMensaje\": \""+textoMensaje+"\"}");
            textoEditMensaje.setText("");
            new mensajesGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+idPartido+"/chat");

        }



    }

    public void informarMensajeEnviado(String result) {
        if (result.equals("OK")){
            context = getApplicationContext();
            CharSequence text = "Mensaje enviado!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


}

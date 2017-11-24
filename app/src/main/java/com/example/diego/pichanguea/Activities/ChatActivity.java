package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.AdapterChat;
import com.example.diego.pichanguea.Controllers.Controllers.Get.mensajesGet;
import com.example.diego.pichanguea.Controllers.Controllers.Post.enviarMensajePost;
import com.example.diego.pichanguea.Models.Mensaje;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;

public class ChatActivity extends AppCompatActivity {
    private AdapterChat adapter;
    public Mensaje mensaje=new Mensaje();
    EditText textoEditMensaje;
    private Toast toast;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actualizarChat) {
            new mensajesGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+idPartido+"/chat");
            context = getApplicationContext();
            if(toast!=null){
                toast.cancel();
            }
            CharSequence text = "Actualizado!";
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void mostrarMensajes(String result) {
        if (result != null) {


            JsonHandler jh = new JsonHandler();
            String[] listaMensajes = jh.getChat(result);
            ListView mensajesListView = (ListView) findViewById(R.id.listMensajes);

            adapter = new AdapterChat(this, listaMensajes);
            mensajesListView.setAdapter(adapter);

        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "No hay mensajes para mostrar!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
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
            if(toast!=null){
                toast.cancel();
            }
            CharSequence text = "Mensaje enviado!";
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


}

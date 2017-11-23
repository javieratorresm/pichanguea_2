package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.AdapterJugador;
import com.example.diego.pichanguea.Controllers.Controllers.Post.confirmarPost;
import com.example.diego.pichanguea.Controllers.Controllers.Put.modificarAsistenciaPut;
import com.example.diego.pichanguea.Controllers.Controllers.Get.jugadoresGet;
import com.example.diego.pichanguea.Models.Equipo;
import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.TipoPartido;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;


public class InfoPartidoActivity extends AppCompatActivity {
    Partido partido=new Partido();
    Usuario usuario=new Usuario();
    TipoPartido tipoPartido=new TipoPartido();
    Equipo equipo=new Equipo();
    JsonHandler jh= new JsonHandler();
    String asistencia;
    private String[] jugadoresPartido;
    private LinearLayout layoutAnimado;

    private LinearLayout layoutModificarGalletas;
    private int cantidadGalletas=0;
    private String info;
    String resultado;
    private int numeroJugadores;
    private String idUsuario;
    private AdapterJugador adapter;
    private Context context;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_partido);
        layoutAnimado = (LinearLayout) findViewById(R.id.confirmacion);
        info=getIntent().getExtras().getString("info");
        int posicion=Integer.parseInt(getIntent().getExtras().getString("posicion"));
        idUsuario=getIntent().getExtras().getString("idUsuario");
        resultado=getIntent().getExtras().getString("parametro");
        usuario.setId(String.valueOf(Math.round(Float.valueOf(idUsuario))));
        jh.getPartido(info,posicion,partido,tipoPartido,equipo);
        TextView tipoPartidoView=(TextView) findViewById(R.id.textTipoPartido);
        TextView fechaView=(TextView) findViewById(R.id.textFecha);
        TextView complejoView=(TextView) findViewById(R.id.textComplejo);
        TextView canchaView=(TextView) findViewById(R.id.textCancha);
        TextView equipoView=(TextView) findViewById(R.id.textNombreEquipo);
        TextView horaView=(TextView) findViewById(R.id.textHora);
        layoutModificarGalletas=(LinearLayout) findViewById(R.id.layoutModificarGalleta);
        TextView cantidadGalletas2=(TextView)findViewById(R.id.textCantidadGalletas2);

        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));

        if(tipoPartido.getIdTipoPartido().equals("7.0")){
            tipoPartidoView.setText("Partido nacional");
            numeroJugadores=14;
        }
        else if(tipoPartido.getIdTipoPartido().equals("10.0")){;
            tipoPartidoView.setText("Partido internacional");
            numeroJugadores=11;
        }
        fechaView.setText(partido.getParDia()+" / "+partido.getParMes()+" / "+partido.getParAno());
        complejoView.setText(partido.getParComplejo());
        equipoView.setText(partido.getEquipo().getEquNombre());
        horaView.setText(partido.getParHoras()+":"+partido.getParMinutos());

        if(!partido.getParCancha().equals("")){
            canchaView.setVisibility(View.VISIBLE);
            canchaView.setText(partido.getParCancha());
        }

        //Codigo verificar si aun no acepta
        if (!partido.getAsistencia().equals("1.0")) {
            if (layoutAnimado.getVisibility() == View.GONE) {
                animar(true,"confirmar");
                layoutAnimado.setVisibility(View.VISIBLE);
            }


        }
        else{
            cantidadGalletas=partido.getGalletasCarga();
            cantidadGalletas2.setText(Integer.toString(cantidadGalletas));
        }


        new jugadoresGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+partido.getIdPartido()+"/jugadores/confirmados");








    }
    @Override
    public void onBackPressed() {
        if (layoutAnimado.getVisibility()==View.VISIBLE){
            animar(false,"confirmar");
            layoutAnimado.setVisibility(View.GONE);


        }
        else if(layoutModificarGalletas.getVisibility()==View.VISIBLE){
            animar(false,"modificar");
            layoutModificarGalletas.setVisibility(View.GONE);
        }
        else{
            Intent act=new Intent(this,MenuActivity.class);
            System.out.println(info);
            act.putExtra("parametro", resultado);
            startActivity(act);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jugadores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemModificarGalletas) {
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido,numeroJugadores);
            listaJugadores.setAdapter(adapter);
            if (layoutModificarGalletas.getVisibility() == View.GONE) {
                animar(true,"modificar");
                layoutModificarGalletas.setVisibility(View.VISIBLE);
            }

            return true;
        }
        else if(id == R.id.itemCancelarAsistencia){
            cancelarAsistencia();
        }

        return super.onOptionsItemSelected(item);
    }

    public void confirmarJugador(View view) {

        if (layoutAnimado.getVisibility() == View.VISIBLE){
            animar(false,"confirmar");
            layoutAnimado.setVisibility(View.GONE);
        }
        if(partido.getAsistencia().equals("2.0")) {
            new confirmarPost().execute(getResources().getString(R.string.servidor) + "api/Jugador/" + idUsuario + "/Partidos/" + partido.getIdPartido() + "/Confirmar/1/Galletas/" + Integer.toString(cantidadGalletas), "");
            new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
            partido.setAsistencia("1.0");
        }
        else if (partido.getAsistencia().equals("0.0")) {
            new modificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+idUsuario+"/Partidos/"+partido.getIdPartido()+"/Confirmar/1/Galletas/"+Integer.toString(cantidadGalletas),"");
            new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
            partido.setAsistencia("1.0");

        }
        TextView textCantidadGalletas2=(TextView)findViewById(R.id.textCantidadGalletas2);
        textCantidadGalletas2.setText(Integer.toString(cantidadGalletas));



    }
    private void animar(boolean mostrar,String tipo)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        if (mostrar)
        {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,0.0f);
        }
        else
        {    //desde la esquina superior izquierda a la esquina inferior derecha
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        }
        //duración en milisegundos
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
        if(tipo.equals("confirmar")) {
            layoutAnimado.setLayoutAnimation(controller);
            layoutAnimado.startAnimation(animation);
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido,numeroJugadores);
            listaJugadores.setAdapter(adapter);
        }
        else if(tipo.equals("modificar")){
            layoutModificarGalletas.setLayoutAnimation(controller);
            layoutModificarGalletas.startAnimation(animation);
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido,numeroJugadores);
            listaJugadores.setAdapter(adapter);
        }
    }

    public void aumentarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        TextView textCantidadGalletas2=(TextView)findViewById(R.id.textCantidadGalletas2);
        cantidadGalletas+=1;
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));
        textCantidadGalletas2.setText(String.valueOf(cantidadGalletas));



    }

    public void restarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        TextView textCantidadGalletas2=(TextView)findViewById(R.id.textCantidadGalletas2);
        if(cantidadGalletas>0){
            cantidadGalletas-=1;

        }
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));
        textCantidadGalletas2.setText(String.valueOf(cantidadGalletas));

    }

    public void mostrarJugadores(String result) {
        if (result!=null){
            jugadoresPartido=jh.getJugadores(result);
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido,numeroJugadores);
            listaJugadores.setAdapter(adapter);
        }


    }

    public void abrirChat(View view) {
        if(!partido.getAsistencia().equals("2.0")) {
            Intent act = new Intent(this, ChatActivity.class);
            act.putExtra("idUsuario", usuario.getId());
            act.putExtra("idPartido", partido.getIdPartido());
            startActivity(act);
        }
        else{
            context = getApplicationContext();
            if(toast!=null){
                toast.cancel();
            }
            CharSequence text = "Aun no confirmas asistencia...";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void cancelarAsistencia() {
        new modificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+idUsuario+"/Partidos/"+partido.getIdPartido()+"/Confirmar/0","");
        Intent act=new Intent(this,MenuActivity.class);
        act.putExtra("parametro", resultado);
        startActivity(act);
    }

    public void ModificarGalletasBoton(View view) {
        if (layoutModificarGalletas.getVisibility() == View.VISIBLE){
            animar(false,"modificar");
            layoutModificarGalletas.setVisibility(view.GONE);

        }

        new modificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+idUsuario+"/Partidos/"+partido.getIdPartido()+"/Galletas/"+Integer.toString(cantidadGalletas),"");

        new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");

    }

    public void actualizarJugadores(View view) {
        new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
        context = getApplicationContext();
        if(toast!=null){
            toast.cancel();
        }
        CharSequence text = "Actualizado!";
        int duration = Toast.LENGTH_SHORT;

        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

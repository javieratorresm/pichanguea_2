package com.example.diego.pichanguea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.pichanguea.Classes.AdapterJugador;
import com.example.diego.pichanguea.Controllers.Get.Post.confirmarPost;
import com.example.diego.pichanguea.Controllers.Get.Put.modificarAsistenciaPut;
import com.example.diego.pichanguea.Controllers.Get.Get.jugadoresGet;
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
    private LinearLayout layoutAnimado;
    private LinearLayout layoutCancelar;
    private int cantidadGalletas=0;
    private String info;
    String resultado;
    private int numeroJugadores;
    private String idUsuario;
    private AdapterJugador adapter;

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
        layoutCancelar=(LinearLayout) findViewById(R.id.layoutCancelar);

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
                animar(true);
                layoutAnimado.setVisibility(View.VISIBLE);
            }
            layoutCancelar.setVisibility(View.INVISIBLE);

        }


        new jugadoresGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+partido.getIdPartido()+"/jugadores/confirmados");








    }
    @Override
    public void onBackPressed() {
        Intent act=new Intent(this,MenuActivity.class);
        System.out.println(info);
        act.putExtra("parametro", resultado);
        startActivity(act);
    }


    public void confirmarJugador(View view) {

        if (layoutAnimado.getVisibility() == View.VISIBLE){
            animar(false);
            layoutAnimado.setVisibility(view.GONE);
        }
        if(partido.getAsistencia().equals("2.0")) {
            new confirmarPost().execute(getResources().getString(R.string.servidor) + "api/Jugador/" + idUsuario + "/Partidos/" + partido.getIdPartido() + "/Confirmar/1/Galletas/" + Integer.toString(cantidadGalletas), "");
            new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
        }
        else if (partido.getAsistencia().equals("0.0")) {
            new modificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+idUsuario+"/Partidos/"+partido.getIdPartido()+"/Confirmar/1/Galletas/"+Integer.toString(cantidadGalletas),"");
            new jugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");

        }
        layoutCancelar.setVisibility(View.VISIBLE);


    }
    private void animar(boolean mostrar)
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

        layoutAnimado.setLayoutAnimation(controller);
        layoutAnimado.startAnimation(animation);
    }

    public void aumentarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        cantidadGalletas+=1;
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));


    }

    public void restarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        if(cantidadGalletas>0){
            cantidadGalletas-=1;

        }
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));
    }

    public void mostrarJugadores(String result) {
        if (result!=null){
            String[] jugadoresPartido=jh.getJugadores(result);
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido,numeroJugadores);
            listaJugadores.setAdapter(adapter);
        }


    }

    public void abrirChat(View view) {

        Intent act=new Intent(this,ChatActivity.class);
        startActivity(act);
    }

    public void cancelarAsistencia(View view) {
        new modificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+idUsuario+"/Partidos/"+partido.getIdPartido()+"/Confirmar/0","");
        Intent act=new Intent(this,MenuActivity.class);
        act.putExtra("parametro", resultado);
        startActivity(act);
    }
}

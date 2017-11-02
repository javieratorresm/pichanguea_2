package com.example.diego.pichanguea.Activities;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.pichanguea.Models.Equipo;
import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.TipoPartido;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;


public class InfoPartidoActivity extends AppCompatActivity {
    Partido partido=new Partido();
    TipoPartido tipoPartido=new TipoPartido();
    Equipo equipo=new Equipo();
    JsonHandler jh= new JsonHandler();
    String asistencia;
    private LinearLayout layoutAnimado;
    private int cantidadGalletas=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_partido);
        layoutAnimado = (LinearLayout) findViewById(R.id.confirmacion);
        String info=getIntent().getExtras().getString("info");
        int posicion=Integer.parseInt(getIntent().getExtras().getString("posicion"));
        String idUsuario=getIntent().getExtras().getString("idUsuario");
        jh.getPartido(info,posicion,partido,tipoPartido,equipo);
        TextView tipoPartidoView=(TextView) findViewById(R.id.textTipoPartido);
        TextView fechaView=(TextView) findViewById(R.id.textFecha);
        TextView complejoView=(TextView) findViewById(R.id.textComplejo);
        TextView canchaView=(TextView) findViewById(R.id.textCancha);
        TextView equipoView=(TextView) findViewById(R.id.textNombreEquipo);
        TextView horaView=(TextView) findViewById(R.id.textHora);
        ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));
        //ImageButton botonAgregar=(ImageButton)findViewById(R.id.masButton);
        //ImageButton botonRestar=(ImageButton)findViewById(R.id.menosButton);
        if(tipoPartido.getIdTipoPartido().equals("7.0")){
            tipoPartidoView.setText("Partido nacional");
        }
        else if(tipoPartido.getIdTipoPartido().equals("10.0")){;
            tipoPartidoView.setText("Partido internacional");
        }
        fechaView.setText(partido.getParFecha());
        complejoView.setText(partido.getParComplejo());
        equipoView.setText(partido.getEquipo().getEquNombre());
        horaView.setText(partido.getParHora());

        if(!partido.getParCancha().equals("")){
            canchaView.setVisibility(View.VISIBLE);
            canchaView.setText(partido.getParCancha());
        }

        //Codigo verificar si aun no acepta
        if (layoutAnimado.getVisibility() == View.GONE)
        {
            animar(true);
            layoutAnimado.setVisibility(View.VISIBLE);
        }
        String[] values = new String[] { "Neymar Jr",
                "Marcelo Salas",
                "Diego Maradona",
                "Alexis Sanchez",
                "Ronaldo",
                "Pele",
                "Gary Medel",
                "Marcelo Diaz",
                "Arturo Vidal",
                "Claudio Bravo"
        };
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.elemento_jugador,R.id.nombreJugadorList,values);
        listaJugadores.setAdapter(adapter);

        int count=listaJugadores.getAdapter().getCount();
        System.out.println("cantidad elementos");
        System.out.println(count);
        for(int i=0;i<count;i++){

            View view=listaJugadores.getChildAt(i);
            //view.setBackgroundColor(0x000000);
            //ViewGroup row = (ViewGroup) listaJugadores.getChildAt(i);

            //System.out.println(row.getId());
        }




    }


    public void confirmarJugador(View view) {

        if (layoutAnimado.getVisibility() == View.VISIBLE){
            animar(false);
            layoutAnimado.setVisibility(view.GONE);
        }

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
}

package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.AdapterJugador;
import com.example.diego.pichanguea.Classes.Singleton;
import com.example.diego.pichanguea.Controllers.Controllers.Post.ConfirmarPost;
import com.example.diego.pichanguea.Controllers.Controllers.Post.NotificacionJugadorPost;
import com.example.diego.pichanguea.Controllers.Controllers.Put.ModificarAsistenciaPut;
import com.example.diego.pichanguea.Controllers.Controllers.Get.JugadoresGet;
import com.example.diego.pichanguea.Models.Equipo;
import com.example.diego.pichanguea.Models.NotificacionJugador;
import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.TipoPartido;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;


public class InfoPartidoActivity extends AppCompatActivity {
    Partido partido=new Partido();
    Usuario usuario=new Usuario();
    TipoPartido tipoPartido=new TipoPartido();
    Equipo equipo=new Equipo();
    NotificacionJugador notificacionJugador;
    JsonHandler jh= new JsonHandler();
    String asistencia;
    private String[] jugadoresPartido;

    private int cantidadGalletas=0;
    private String datosPartidos;
    String resultado;
    private int numeroJugadores;
    private String idUsuario;
    private AdapterJugador adapter;
    private Context context;
    private Toast toast;
    private int jugadoresMasGalletas;
    private String topic;
    private String nombreUsuario;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_partido);

        usuario= Singleton.getInstance().getUsuario();

        partido=Singleton.getInstance().getPartido();
        tipoPartido=partido.getTipoPartido();
        equipo=partido.getEquipo();
        topic="/topics/pichanguea_partido_id_"+partido.getIdPartido();

        TextView tipoPartidoView=(TextView) findViewById(R.id.textTipoPartido);
        TextView fechaView=(TextView) findViewById(R.id.textFecha);
        TextView complejoView=(TextView) findViewById(R.id.textComplejo);
        TextView canchaView=(TextView) findViewById(R.id.textCancha);
        TextView equipoView=(TextView) findViewById(R.id.textNombreEquipo);
        TextView horaView=(TextView) findViewById(R.id.textHora);



        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view =getSupportActionBar().getCustomView();



        if(tipoPartido.getIdTipoPartido().equals("7.0")){
            tipoPartidoView.setText("Partido nacional");
            numeroJugadores=14;
        }
        else if(tipoPartido.getIdTipoPartido().equals("10.0")){;
            tipoPartidoView.setText("Partido internacional");
            numeroJugadores=11;
        }
        fechaView.setText(partido.getParDia()+"/"+partido.getParMes()+"/"+partido.getParAno());
        complejoView.setText(partido.getParComplejo());
        equipoView.setText(partido.getEquipo().getEquNombre());
        horaView.setText(partido.getParHoras()+":"+partido.getParMinutos()+" Hrs");

        if(!partido.getParCancha().equals("")){
            canchaView.setVisibility(View.VISIBLE);
            canchaView.setText(partido.getParCancha());
        }

        //Codigo verificar si aun no acepta
        actualizarBotones();


        new JugadoresGet(this).execute(getResources().getString(R.string.servidor)+"api/partido/"+partido.getIdPartido()+"/jugadores/confirmados");



        Button botonAceptar=(Button)findViewById(R.id.botonConfirmar);
        Button botonModificar=(Button)findViewById(R.id.botonModificar);
        Button botonCancelar=(Button)findViewById(R.id.botonCancelar);
        ImageButton botonSumar=(ImageButton)findViewById(R.id.masButton);
        ImageButton botonRestar=(ImageButton)findViewById(R.id.menosButton);


        if (!partido.getAsistencia().equals("1.0")) {

            botonAceptar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonSumar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonRestar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonModificar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);

            botonCancelar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);
            textCantidadGalletas.setText(String.valueOf(cantidadGalletas));




        }
        else{
            cantidadGalletas=partido.getGalletasCarga();
            textCantidadGalletas.setText(String.valueOf(cantidadGalletas));

            botonAceptar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);

            botonModificar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);
            botonSumar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);
            botonRestar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);

            botonCancelar.getBackground().setColorFilter(getResources().getColor(R.color.colorRojo), PorterDuff.Mode.DARKEN);


        }




    }
    public void actualizarBotones(){
        Button botonAceptar=(Button)findViewById(R.id.botonConfirmar);
        Button botonModificar=(Button)findViewById(R.id.botonModificar);
        Button botonCancelar=(Button)findViewById(R.id.botonCancelar);
        ImageButton botonSumar=(ImageButton)findViewById(R.id.masButton);
        ImageButton botonRestar=(ImageButton)findViewById(R.id.menosButton);


        if (!partido.getAsistencia().equals("1.0")) {

            botonAceptar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonSumar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonRestar.getBackground().setColorFilter(getResources().getColor(R.color.colorVerdeIntenso), PorterDuff.Mode.DARKEN);
            botonModificar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);

            botonCancelar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);





        }
        else{

            botonAceptar.getBackground().setColorFilter(getResources().getColor(R.color.colorGrisSuave), PorterDuff.Mode.DARKEN);

            botonModificar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);
            botonSumar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);
            botonRestar.getBackground().setColorFilter(getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.DARKEN);

            botonCancelar.getBackground().setColorFilter(getResources().getColor(R.color.colorRojo), PorterDuff.Mode.DARKEN);


        }



    }
    @Override
    public void onBackPressed() {


        Intent act=new Intent(this,MenuActivity.class);
        act.putExtra("parametro", resultado);
        startActivity(act);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemModificarGalletas) {
            if (partido.getAsistencia().equals("1.0")) {
                ListView listaJugadores = (ListView) findViewById(R.id.listaJugadores);
                adapter = new AdapterJugador(this, jugadoresPartido);
                listaJugadores.setAdapter(adapter);



                return true;
            }
            else{
                context = getApplicationContext();
                CharSequence text = "Aun no confirmas asistencia";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;

            }
        }
        else if(id == R.id.itemCancelarAsistencia){
            if(partido.getAsistencia().equals("1.0")){

            }
            else{
                context = getApplicationContext();
                CharSequence text = "Aun no confirmas asistencia";
                int duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    public void confirmarJugador(View view) {
        if(!partido.getAsistencia().equals("1.0")){
            if(partido.getAsistencia().equals("2.0")) {
                new ConfirmarPost().execute(getResources().getString(R.string.servidor) + "api/Jugador/" + usuario.getId() + "/Partidos/" + partido.getIdPartido() + "/Confirmar/1/Galletas/" + Integer.toString(cantidadGalletas), "");
                new JugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
                partido.setAsistencia("1.0");

            }
            else if (partido.getAsistencia().equals("0.0")) {
                new ModificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+usuario.getId()+"/Partidos/"+partido.getIdPartido()+"/Confirmar/1/Galletas/"+Integer.toString(cantidadGalletas),"");
                new JugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
                partido.setAsistencia("1.0");

            }
            FirebaseMessaging.getInstance().subscribeToTopic("pichanguea_partido_id_"+partido.getIdPartido());
            actualizarBotones();

        }
        else{
            context = getApplicationContext();
            CharSequence text = "Ya estas confirmado";
            int duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, text, duration);
            toast.show();

        }






    }
    public void cancelarAsistencia(View view) {
        if(partido.getAsistencia().equals("1.0")){
            FirebaseMessaging.getInstance().unsubscribeFromTopic("pichanguea_partido_id_"+partido.getIdPartido());
            notificacionJugador=new NotificacionJugador(idUsuario,partido.getIdPartido(),equipo.getEquNombre()+", "+partido.getParDia()+"/"+partido.getParMes()+"/"+partido.getParAno()+", "+partido.getParHoras()+":"+partido.getParMinutos()+ " Hrs",usuario.getNombre()+" "+usuario.getPaterno()+" ha cancelado asistencia",usuario.getId(),topic);
            JsonHandler jh= new JsonHandler();
            JSONObject jo=jh.setNotificacionJugador(notificacionJugador);
            new NotificacionJugadorPost().execute(getResources().getString(R.string.fcm_server),jo.toString());
            new ModificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+usuario.getId()+"/Partidos/"+partido.getIdPartido()+"/Confirmar/0","");




            Intent act=new Intent(this,MenuActivity.class);
            //act.putExtra("parametro", resultado);

            startActivity(act);

        }
        else{
            context = getApplicationContext();
            CharSequence text = "Aun no confirmas asistencia";
            int duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }

    public void ModificarGalletas(View view) {
        if(partido.getAsistencia().equals("1.0")){

            new ModificarAsistenciaPut().execute(getResources().getString(R.string.servidor)+"api/Jugador/"+usuario.getId()+"/Partidos/"+partido.getIdPartido()+"/Galletas/"+Integer.toString(cantidadGalletas),"");

            new JugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
        }
        else{
            context = getApplicationContext();
            CharSequence text = "Aun no confirmas asistencia";
            int duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, text, duration);
            toast.show();

        }




    }

    public void aumentarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);

        if(partido.getAsistencia().equals("1.0")) {
            if (jugadoresMasGalletas < numeroJugadores) {

                cantidadGalletas += 1;
                jugadoresMasGalletas += 1;
                textCantidadGalletas.setText(String.valueOf(cantidadGalletas));

            } else {
                context = getApplicationContext();
                CharSequence text = "No puedes agregar más galletas";
                int duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
        else{
            if (jugadoresMasGalletas +1 < numeroJugadores) {

                cantidadGalletas += 1;
                jugadoresMasGalletas += 1;
                textCantidadGalletas.setText(String.valueOf(cantidadGalletas));

            } else {
                context = getApplicationContext();
                CharSequence text = "No puedes agregar más galletas";
                int duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        }




    }

    public void restarGalleta(View view) {
        TextView textCantidadGalletas=(TextView)findViewById(R.id.textCantidadGalletas);

        if(cantidadGalletas>0){
            cantidadGalletas-=1;
            jugadoresMasGalletas-=1;

        }
        textCantidadGalletas.setText(String.valueOf(cantidadGalletas));


    }

    public void mostrarJugadores(String result) {
        jugadoresMasGalletas=0;
        String[] separado;
        int galleta;
        if (result!=null){
            jugadoresPartido=jh.getJugadores(result);

            for(int i=0;i<jugadoresPartido.length;i++){
                separado=jugadoresPartido[i].split("@#");
                galleta=Math.round(Float.valueOf(separado[1]));
                jugadoresMasGalletas+=1+galleta;
                if(jugadoresMasGalletas<=numeroJugadores){
                    jugadoresPartido[i]=jugadoresPartido[i]+"@#"+"1";
                }
                else{
                    jugadoresPartido[i]=jugadoresPartido[i]+"@#"+"0";
                }


            }
            ListView listaJugadores=(ListView)findViewById(R.id.listaJugadores);
            adapter = new AdapterJugador(this,jugadoresPartido);
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



    public void actualizarJugadores(View view) {
        new JugadoresGet(this).execute(getResources().getString(R.string.servidor) + "api/partido/" + partido.getIdPartido() + "/jugadores/confirmados");
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

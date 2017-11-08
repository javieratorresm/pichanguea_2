package com.example.diego.pichanguea.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.pichanguea.Classes.AdapterPartido;
import com.example.diego.pichanguea.Controllers.Get.jugadoresGet;
import com.example.diego.pichanguea.Controllers.Get.partidosGet;
import com.example.diego.pichanguea.Controllers.Get.sesionGet;
import com.example.diego.pichanguea.Models.Partido;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Partido partido=new Partido();
    Usuario usuario = new Usuario();
    String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tus Partidos");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */
        //ListView simpleList;

        //View header=navigationView.getHeaderView(0);
        resultado=getIntent().getExtras().getString("parametro");
        JsonHandler jh= new JsonHandler();


        jh.getInformacion(resultado,usuario );


        new partidosGet(this).execute(getResources().getString(R.string.servidor)+"api/Jugador/1/Partidos/");






    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent act=new Intent(this,LoginActivity.class);
            startActivity(act);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_equipos) {
            // Handle the camera action
        } else if (id == R.id.nav_solicitudes) {

        } else if (id == R.id.nav_partidos) {

        } else if (id == R.id.nav_sin_responder) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void mostrarPartidos(final String result) {

        JsonHandler jh= new JsonHandler();
        String[] listaPartidos=jh.getPartidos(result);
        ListView simpleList = (ListView) findViewById(R.id.listPartidos);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview_layout,R.id.textView,listaPartidos);
        AdapterPartido adapter=new AdapterPartido(this,listaPartidos);
        final Intent act=new Intent(this,InfoPartidoActivity.class);

        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

                act.putExtra("info",result);
                System.out.println(result);

                act.putExtra("posicion",String.valueOf(arg2));
                System.out.println(String.valueOf(arg2));
                act.putExtra("idUsuario",usuario.getId());
                System.out.println(usuario.getId());
                act.putExtra("parametro",resultado);
                System.out.println(resultado);
                startActivity(act);
                finish();
            }
        });

    }


}

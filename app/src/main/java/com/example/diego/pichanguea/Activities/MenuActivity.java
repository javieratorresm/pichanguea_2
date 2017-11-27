package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
=======
>>>>>>> fd2a06d2b05bdb2b80410371520f14969201e819
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.AdapterPartido;
import com.example.diego.pichanguea.Classes.SectionsPageAdapter;
import com.example.diego.pichanguea.Controllers.Controllers.Get.partidosGet;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;
import com.onesignal.OneSignal;
<<<<<<< HEAD
import com.example.diego.pichanguea.Classes.SectionsPageAdapter;
import com.example.diego.pichanguea.Classes.Tab1Fragment;
import com.example.diego.pichanguea.Classes.Tab2Fragment;
import com.example.diego.pichanguea.Classes.Tab3Fragment;
import com.example.diego.pichanguea.R;
=======

>>>>>>> fd2a06d2b05bdb2b80410371520f14969201e819
public class MenuActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG="MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    Tab1Fragment tab1Fragment=new Tab1Fragment();
    Tab2Fragment tab2Fragment=new Tab2Fragment();
    Tab3Fragment tab3Fragment=new Tab3Fragment();
    Usuario usuario = new Usuario();
    String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_menu1);
        setContentView(R.layout.activity_tab_partidos);
        Log.d(TAG,"onCreate:Starting");
        mSectionsPageAdapter=new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        //setupViewPager(mViewPager);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tus Partidos");
        //ONE SIGNAL!!
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);

        //one signal
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);


        resultado=getIntent().getExtras().getString("parametro");
        JsonHandler jh= new JsonHandler();


        jh.getInformacion(resultado,usuario );


        new partidosGet(this).execute(getResources().getString(R.string.servidor)+"api/Jugador/"+usuario.getId()+"/Partidos");







    }

    private void setupViewPager(ViewPager viewPager,final String result,final String[] listaPartidos){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(tab1Fragment,"Confirmados");
        adapter.addFragment(tab2Fragment,"Sin ver");
        adapter.addFragment(tab3Fragment,"Cancelados");
        viewPager.setAdapter(adapter);

        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) { }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageSelected(int position) {
                System.out.println(position);
                switch (position) {
                    case 0:
                        tab1Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);

                        break;

                    case 1:

                        tab2Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);
                        break;
                    case 2:
                        tab3Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);
                        break;
                    default:
                        break;


                }
            }
        };
        viewPager.setOnPageChangeListener(pageChangeListener);




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

        return true;
    }


    public void mostrarPartidos(final String result) {

        if (result != null) {
            JsonHandler jh = new JsonHandler();
            String[] listaPartidos = jh.getPartidos(result);


            setupViewPager(mViewPager,result,listaPartidos);

            tab1Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);
            //tab1Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);
            //tab2Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);
            //tab3Fragment.mostrarPartidos(result,listaPartidos,resultado,usuario);



            /*
            AdapterPartido adapter = new AdapterPartido(this, listaPartidos);
            final Intent act = new Intent(this, InfoPartidoActivity.class);

            simpleList.setAdapter(adapter);

            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                    act.putExtra("info", result);
                    act.putExtra("posicion", String.valueOf(arg2));
                    act.putExtra("idUsuario", usuario.getId());
                    act.putExtra("parametro", resultado);
                    startActivity(act);
                    finish();
                }
            });
            */

        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Fallo en la conexión!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }



}

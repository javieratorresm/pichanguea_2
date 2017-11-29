package com.example.diego.pichanguea.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.diego.pichanguea.Classes.Singleton;
import com.example.diego.pichanguea.Classes.SectionsPageAdapter;
import com.example.diego.pichanguea.Controllers.Controllers.Get.PartidosGet;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;
import com.example.diego.pichanguea.Utilities.JsonHandler;

import com.example.diego.pichanguea.Classes.Tab1Fragment;
import com.example.diego.pichanguea.Classes.Tab2Fragment;
import com.example.diego.pichanguea.Classes.Tab3Fragment;

public class MenuActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG="MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    Tab1Fragment tab1Fragment=new Tab1Fragment();
    Tab2Fragment tab2Fragment=new Tab2Fragment();
    Tab3Fragment tab3Fragment=new Tab3Fragment();
    Usuario usuario = new Usuario();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tab_partidos);
        usuario= Singleton.getInstance().getUsuario();


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





        new PartidosGet(this).execute(getResources().getString(R.string.servidor)+"api/Jugador/"+usuario.getId()+"/Partidos");







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
                switch (position) {
                    case 0:
                        tab1Fragment.mostrarPartidos(listaPartidos);

                        break;

                    case 1:

                        tab2Fragment.mostrarPartidos(listaPartidos);
                        break;
                    case 2:
                        tab3Fragment.mostrarPartidos(listaPartidos);
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
            Singleton.getInstance().setToken("0");
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
            Singleton.getInstance().setDatosPartidos(result);
            tab1Fragment.mostrarPartidos(listaPartidos);

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

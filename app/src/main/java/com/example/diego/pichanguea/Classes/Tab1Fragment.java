package com.example.diego.pichanguea.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.diego.pichanguea.Activities.InfoPartidoActivity;
import com.example.diego.pichanguea.Models.Usuario;
import com.example.diego.pichanguea.R;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private String resultado;
    private String result;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu1,container,false);
        return view;
    }
    public void mostrarPartidos(final String result, final String[] listaPartidos, final String resultado, final Usuario usuario){



        String [] separado;
        int i;

        ArrayList<String> listaModificada = new ArrayList<String>();
        for(i=0;i<listaPartidos.length;i++){
            //asistencia en separado[3]

            separado=listaPartidos[i].split("@#");

            if(separado[3].equals("1.0")){

               listaModificada.add(listaPartidos[i]);
            }
            //listaModificada.add(listaPartidos[i]);
        }

        AdapterPartido adapter = new AdapterPartido(getContext(), listaModificada);
        final Intent act = new Intent(getContext(), InfoPartidoActivity.class);
        ListView simpleList = (ListView) getView().findViewById(R.id.listPartidos1);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String [] separado;
                int position = 0;
                int contador=0;

                for(int i=0;i<listaPartidos.length;i++){
                    separado=listaPartidos[i].split("@#");

                    if(separado[3].equals("1.0")){
                        contador+=1;
                    }

                    if (contador==arg2+1){
                        position=i;
                        break;
                    }


                }
                System.out.println("position");
                System.out.println(position);
                act.putExtra("info", result);
                act.putExtra("posicion", String.valueOf(position));
                act.putExtra("idUsuario", usuario.getId());
                act.putExtra("parametro", resultado);
                startActivity(act);


            }
        });
    }
}
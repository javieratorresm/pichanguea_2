package com.example.diego.pichanguea.Classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diego.pichanguea.R;


/**
 * Created by Diego on 08-11-2017.
 */

public class AdapterJugador extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final int numeroJugadores;
    private int jugadoresMasGalleta=0;
    String[] separado;
    int galleta;

    public AdapterJugador(Context context, String[] values,int numeroJugadores) {
        super(context, R.layout.elemento_jugador, values);
        this.context = context;
        this.values = values;
        this.numeroJugadores=numeroJugadores;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_jugador, parent, false);
        TextView nombreJugador = (TextView) rowView.findViewById(R.id.nombreJugadorList);
        TextView textGalleta=(TextView) rowView.findViewById(R.id.numeroGalletas);


        separado=values[position].split("@#");
        galleta=Math.round(Float.valueOf(separado[1]));

        nombreJugador.setText(separado[0]);
        jugadoresMasGalleta+=1+galleta;
        if(jugadoresMasGalleta<=numeroJugadores){
            nombreJugador.setBackgroundColor(Color.parseColor("#3ee210"));
        }
        else{
            nombreJugador.setBackgroundColor(Color.parseColor("#fff200"));
        }

        if (galleta==0){
            textGalleta.setText("");
        }
        else{
            textGalleta.setText("+"+String.valueOf(galleta));
        }

        return rowView;
    }
}


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
    private final int[] listaGalletas;
    private final int numeroJugadores;
    private int jugadoresMasGalleta=0;

    public AdapterJugador(Context context, String[] values,int[] listaGalletas,int numeroJugadores) {
        super(context, R.layout.elemento_jugador, values);
        this.context = context;
        this.values = values;
        this.numeroJugadores=numeroJugadores;
        this.listaGalletas=listaGalletas;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_jugador, parent, false);
        TextView nombreJugador = (TextView) rowView.findViewById(R.id.nombreJugadorList);
        TextView textGalleta=(TextView) rowView.findViewById(R.id.numeroGalletas);

        if(jugadoresMasGalleta<numeroJugadores){
            nombreJugador.setBackgroundColor(Color.parseColor("#3ee210"));
        }
        else{
            nombreJugador.setBackgroundColor(Color.parseColor("#fff200"));
        }
        jugadoresMasGalleta+=1+listaGalletas[position];
        nombreJugador.setText(values[position]);
        textGalleta.setText("+"+String.valueOf(listaGalletas[position]));
        return rowView;
    }
}


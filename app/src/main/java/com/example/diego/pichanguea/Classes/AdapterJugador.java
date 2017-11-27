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

    String[] separado;
    int galleta;



    public AdapterJugador(Context context, String[] values) {
        super(context, R.layout.elemento_jugador, values);
        this.context = context;
        this.values = values;



    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_jugador, parent, false);
        TextView nombreJugador = (TextView) rowView.findViewById(R.id.nombreJugadorList);
        TextView textGalleta=(TextView) rowView.findViewById(R.id.numeroGalletas);


        separado=values[position].split("@#");
        nombreJugador.setText(separado[0]);
        galleta=Math.round(Float.valueOf(separado[1]));
        if(separado[2].equals("1")){

            nombreJugador.setBackgroundColor(getContext().getResources().getColor(R.color.colorVerde));
        }
        else{
           nombreJugador.setBackgroundColor(getContext().getResources().getColor(R.color.colorAmarillo));
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


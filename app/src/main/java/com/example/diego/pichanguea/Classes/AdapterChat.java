package com.example.diego.pichanguea.Classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.pichanguea.R;

/**
 * Created by Diego on 16-11-2017.
 */

public class AdapterChat extends ArrayAdapter<String> {
    private final Context context;

    private final String[] listaMensajes;
    String[] separado;


    public AdapterChat (Context context, String[] listaMensajes) {
        super(context, R.layout.elemento_mensaje, listaMensajes);
        this.context = context;
        this.listaMensajes=listaMensajes;


    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_mensaje, parent, false);



        TextView nombreJugador = (TextView) rowView.findViewById(R.id.nombreJugador);
        TextView textoMensaje=(TextView) rowView.findViewById(R.id.textoMensaje);
        separado=listaMensajes[position].split("@#");
        nombreJugador.setText(separado[0]);
        textoMensaje.setText(separado[1]);






        return rowView;
    }
}


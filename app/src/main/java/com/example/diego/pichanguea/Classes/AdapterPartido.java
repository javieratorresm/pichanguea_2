package com.example.diego.pichanguea.Classes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.pichanguea.R;

import java.util.ArrayList;

/**
 * Created by Diego on 08-11-2017.
 */

public class AdapterPartido extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> listaPartidos;
    String[] separado;
    String asistencia;
    public AdapterPartido(Context context,ArrayList<String> listaPartidos) {
        super(context, R.layout.elemento_partido,listaPartidos);
        this.context=context;
        this.listaPartidos=listaPartidos;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_partido, parent, false);

        separado= listaPartidos.get(position).split("@#");
        TextView textNombreEquipo=(TextView) rowView.findViewById(R.id.idNombreEquipo);
        TextView textnombreTipoPartido=(TextView) rowView.findViewById(R.id.idNombreTipoPartido);
        TextView textFecha=(TextView) rowView.findViewById(R.id.idFechaPartido);
        asistencia=separado[3];
        //textNombreEquipo.setText(separado[0]+" "+separado[4]);
        textNombreEquipo.setText(separado[0]);
        textnombreTipoPartido.setText(separado[1]);
        textFecha.setText(separado[2]+"  "+separado[5]);
        if (asistencia.equals("2.0")) {
            rowView.getBackground().setColorFilter(getContext().getResources().getColor(R.color.colorAmarillo), PorterDuff.Mode.SRC_ATOP);

        }
        else if(asistencia.equals("1.0")){rowView.getBackground().setColorFilter(getContext().getResources().getColor(R.color.colorVerde), PorterDuff.Mode.SRC_ATOP);
        }
        else if(asistencia.equals("0.0")){rowView.getBackground().setColorFilter(getContext().getResources().getColor(R.color.colorRojo), PorterDuff.Mode.SRC_ATOP);}


        return rowView;
    }
}

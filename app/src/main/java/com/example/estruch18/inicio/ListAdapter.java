package com.example.estruch18.inicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by estruch18 on 26/1/16.
 */
public abstract class ListAdapter extends BaseAdapter{

    private Context context;
    private int idLayout;
    private ArrayList<?> datosEntrada;

    public ListAdapter(Context context, int idLayout, ArrayList<?> datos){
        super();
        this.context = context;
        this.idLayout = idLayout;
        this.datosEntrada = datos;
    }

    @Override
    public int getCount() {
        return this.datosEntrada.size();
    }

    @Override
    public Object getItem(int position) {
        return this.datosEntrada.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            //Acceso a servicio para hinchar layouts
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Hinchamos el layout
            convertView = li.inflate(idLayout, null);
        }

        onEntrada (datosEntrada.get(position), convertView); //Llamada a método "onEntrada()"
        return convertView;
    }

    //Método abstracto "onEntrada"
    public abstract void onEntrada(Object objeto, View v);
}


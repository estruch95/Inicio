package com.example.estruch18.inicio;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Atributos correspondientes al ListView de rutas
    private ListView listaRutas;
    private ArrayList<Elemento> rutas;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejecución de métodos
        declaracionViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void declaracionViews(){
        listaRutas = (ListView)findViewById(R.id.lvRutas);
        rutas = new ArrayList<Elemento>();
        for(int a=0; a<5; a++){
            rutas.add(new Elemento(R.mipmap.ic_launcher, "Titulo", "Descripcion"));
        }

        listaRutas.setAdapter(new ListAdapter(this, R.layout.elemento, rutas) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView tituloRuta = (TextView) view.findViewById(R.id.tituloRuta);
                    if (tituloRuta != null)
                        tituloRuta.setText(((Elemento) entrada).getTituloRuta());

                    TextView descripcionRuta = (TextView) view.findViewById(R.id.descripcionRuta);
                    if (descripcionRuta != null)
                        descripcionRuta.setText(((Elemento) entrada).getDescripcionRuta());

                    ImageView imgRuta = (ImageView) view.findViewById(R.id.imgRuta);
                    if (imgRuta != null)
                        imgRuta.setImageResource(((Elemento) entrada).getImgRuta());
                }
            }
        });
    }

}

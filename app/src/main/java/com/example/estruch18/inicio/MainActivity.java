package com.example.estruch18.inicio;

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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Atributos correspondientes al ImageSwitcher
    private ImageSwitcher imageSwitcher;
    private ArrayList<Integer> imagenes;
    private int position;
    private static final Integer DURATION = 2500;
    private Timer timer = null;

    //Atributos correspondientes al ListView de rutas
    private ListView listaRutas;
    private ArrayList<Elemento> rutas;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejecución de métodos
        startIMGSwitcher();
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

    public void loadIMG(){
        imagenes = new ArrayList<Integer>();
        imagenes.add(R.drawable.fondo1);
        imagenes.add(R.drawable.fondo2);
        imagenes.add(R.drawable.fondo3);
        imagenes.add(R.drawable.fondo4);
        imagenes.add(R.drawable.fondo5);
    }

    public void startIMGSwitcher(){
        loadIMG();
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.abc_popup_enter);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.abc_popup_exit);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

        View v = null;
        start(v);
    }

    public void start(View button) {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }

    public void stop(View button) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(imagenes.get(position));
                        position++;
                        if (position == imagenes.size()) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);
    }

    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

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

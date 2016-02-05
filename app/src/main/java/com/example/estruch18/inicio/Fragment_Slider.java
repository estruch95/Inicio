package com.example.estruch18.inicio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_Slider extends Fragment{

    //Atributos correspondientes al ImageSwitcher
    private ImageSwitcher imageSwitcher;
    ImageSwitcherPicasso mImageSwitcherPicasso;
    private ArrayList<Integer> imagenes;
    private int position;
    private static final Integer DURATION = 2500;
    private Timer timer = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slider, container, false);
        imageSwitcher = (ImageSwitcher)v.findViewById(R.id.imageSwitcher);
        startIMGSwitcher();
        return v;
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

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.slide_in_left);
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.slide_out_right);
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
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mImageSwitcherPicasso = new ImageSwitcherPicasso(getActivity(), imageSwitcher);
                        Picasso.with(getActivity()).load(imagenes.get(position)).into(mImageSwitcherPicasso);
                        position++;
                        if (position == imagenes.size()) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);
    }

    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

    }

}

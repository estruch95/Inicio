package com.example.estruch18.inicio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by estruch18 on 26/1/16.
 */
public class Fragment_Slider extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Unflem el Layout amb el fragment 3
        return inflater.inflate(R.layout.fragment_slider, container, false);
    }

}

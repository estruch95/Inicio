package com.example.estruch18.inicio;

/**
 * Created by estruch18 on 26/1/16.
 */
public class Elemento {

    private int imgRuta;
    private String tituloRuta;
    private String descripcionRuta;

    public Elemento(int imgRuta, String tituloRuta, String descripcionRuta) {
        this.imgRuta = imgRuta;
        this.tituloRuta = tituloRuta;
        this.descripcionRuta = descripcionRuta;
    }

    public String getTituloRuta() {
        return tituloRuta;
    }

    public void setTituloRuta(String tituloRuta) {
        this.tituloRuta = tituloRuta;
    }

    public String getDescripcionRuta() {
        return descripcionRuta;
    }

    public void setDescripcionRuta(String descripcionRuta) {
        this.descripcionRuta = descripcionRuta;
    }

    public int getImgRuta() {
        return imgRuta;
    }

    public void setImgRuta(int imgRuta) {
        this.imgRuta = imgRuta;
    }
}

package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;

import java.util.ArrayList;


public class GestioneGrafica extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {

        Client c = (Client) objects[0];
        ArrayList<Domanda> d = (ArrayList) objects[1];

        d.add( new Domanda(c.getText()) );
        return null;
    }
}

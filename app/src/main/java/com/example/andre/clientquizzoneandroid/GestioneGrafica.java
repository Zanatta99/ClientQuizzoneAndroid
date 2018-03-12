package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.dom.DOMLocator;

public class GestioneGrafica extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {

        Client c = (Client) objects[0];
        ArrayList<Domanda> d = (ArrayList) objects[1];


        d.add( new Domanda( c.getText()) );
        System.out.println("Fatto");
        return null;
    }
}

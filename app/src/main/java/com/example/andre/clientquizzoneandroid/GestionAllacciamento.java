package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;

public class GestionAllacciamento extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        Client c = (Client) objects[0];
        c.attendi();

        return null;
    }
}

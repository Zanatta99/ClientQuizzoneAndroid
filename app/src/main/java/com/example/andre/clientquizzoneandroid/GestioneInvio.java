package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;

public class GestioneInvio extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        Client c = (Client) objects[0];
        String s = (String) objects[1];
        c.invio(s);

        return null;
    }
}

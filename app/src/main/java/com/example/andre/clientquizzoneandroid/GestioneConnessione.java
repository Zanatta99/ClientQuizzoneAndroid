package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;

public class GestioneConnessione extends AsyncTask {

    private Client client;

    GestioneConnessione(Client client)
    {
        this.client = client;
    }

    protected Object doInBackground(Object[] objects) {
        client.creaConnessione();
        return null;
    }
}

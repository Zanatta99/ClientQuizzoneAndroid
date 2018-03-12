package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.IOException;

public class GestioneConnessione extends AsyncTask {

    private Client client;
    private ProgressBar barra;

    GestioneConnessione(Client client, ProgressBar p)
    {
        this.client = client;
        barra = p;
    }

    protected Object doInBackground(Object[] objects) {

        publishProgress(10);
        client.creaConnessione();
        publishProgress(50);

        publishProgress(100);

        return null;
    }

    protected void onProgressUpdate(int prog) {
        barra.setProgress(prog);
    }

    protected void onPostExecute() {
    }
}

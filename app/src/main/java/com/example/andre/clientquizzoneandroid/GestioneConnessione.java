package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class GestioneConnessione extends AsyncTask {

    private Client client;
    private ProgressBar barra;

    GestioneConnessione(Client client, ProgressBar p)
    {
        this.client = client;
        barra = p;
    }

    protected Object doInBackground(Object[] objects) {

        client.creaConnessione();
        publishProgress(10);

        return null;
    }

    protected void onProgressUpdate(int prog) {
        barra.setProgress(prog);
    }

    protected void onPostExecute() {
    }
}

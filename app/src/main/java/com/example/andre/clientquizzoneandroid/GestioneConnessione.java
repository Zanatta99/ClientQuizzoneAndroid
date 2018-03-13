package com.example.andre.clientquizzoneandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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


        //publishProgress(10);
        System.out.println("Inizia");
        client.creaConnessione();
        //publishProgress(50);

        //publishProgress(100);

        return null;
    }

    private void onProgressUpdate(int prog) {
        barra.setProgress(prog);
    }
}

package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class RiceviRisultati extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        Client c = (Client) objects[0];
        Bundle bundle = (Bundle) objects[1];

        bundle.putString("Risultato", c.getText() );
        c.close();

        return null;
    }
}

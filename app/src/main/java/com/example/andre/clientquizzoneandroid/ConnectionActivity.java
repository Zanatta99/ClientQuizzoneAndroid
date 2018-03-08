package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Client c = new Client("localhost");

        ProgressBar barra = (ProgressBar) findViewById(R.id.progresso);

        new GestioneConnessione(c, barra).execute();

        Intent i = new Intent(ConnectionActivity.this, GameActivity.class);
        startActivity(i);
    }
}

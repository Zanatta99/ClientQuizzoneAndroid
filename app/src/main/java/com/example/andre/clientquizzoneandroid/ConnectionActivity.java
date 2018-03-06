package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        System.out.println("Ci provo");
        c.creaConnessione();
        System.out.println("Connessione creata");

        try {
            c.attendi();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(ConnectionActivity.this, GameActivity.class);
        startActivity(i);
    }
}

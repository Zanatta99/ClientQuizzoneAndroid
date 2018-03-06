package com.example.andre.clientquizzoneandroid;

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
        //callSocket();
    }

    private void callSocket()
    {
        try
        {
            InetAddress p = InetAddress.getByName("132.168.1.1");
            int porta = 9999;

            Socket s = new Socket(p, porta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

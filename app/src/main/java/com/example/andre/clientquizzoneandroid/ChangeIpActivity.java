package com.example.andre.clientquizzoneandroid;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class ChangeIpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView textIndirizzo = findViewById(R.id.editIP);
        textIndirizzo.setText("");

        final Button conferma = findViewById(R.id.buttonConferma);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textIndirizzo.getText().equals(""))
                {
                    String ip = (String) textIndirizzo.getText().toString();
                    try {
                        InetAddress indirizzo = InetAddress.getByName(ip);
                        SharedPreferences.Editor editor = getSharedPreferences("Impostazioni", MODE_PRIVATE).edit();
                        editor.putString("Ip", ip);
                        editor.apply();

                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.layout), "Indirizzo IP cambiato", LENGTH_LONG);
                        mySnackbar.show();
                    } catch (Exception e) {
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.layout), "Errore, indirizzo IP non cambiato", LENGTH_LONG);
                        mySnackbar.show();
                    }
                }
            }
        });
    }
}

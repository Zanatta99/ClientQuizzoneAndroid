package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class ConnectionActivity extends AppCompatActivity {

    int cont;
    ArrayList<Button> bottoni;
    ArrayList<Domanda> domande = new ArrayList<>();
    TextView textDomanda;
    Client c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        SharedPreferences preferences = getSharedPreferences("Impostazioni", MODE_PRIVATE);

        if( preferences.getString("Ip", null) == null )
            c = new Client("192.168.1.66");
        else
            c = new Client(preferences.getString("Ip", null));

        ProgressBar barra = findViewById(R.id.progresso);

        new GestioneConnessione(c, barra).execute();

        while( c.isConnected() == -1 );

        System.out.println(c.isConnected());
        if(c.isConnected() == 0)
        {
            Intent i = new Intent(ConnectionActivity.this, StartActivity.class);
            i.putExtra("Errore", true);
            startActivity(i);
        }
        else {

            new GestionAllacciamento().execute(c);

            while (!c.isReady()) ;
            System.out.println(c.isReady());

            setContentView(R.layout.activity_game);

            cont = 0;
            bottoni = new ArrayList<>();

            textDomanda = findViewById(R.id.textViewDomanda);
            Button risp = findViewById(R.id.buttonRisposta1);
            risp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    risposta(1, false);
                }
            });
            bottoni.add(risp);
            risp = findViewById(R.id.buttonRisposta2);
            risp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    risposta(2, false);
                }
            });
            bottoni.add(risp);
            risp = findViewById(R.id.buttonRisposta3);
            risp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    risposta(3, false);
                }
            });
            bottoni.add(risp);
            risp = findViewById(R.id.buttonRisposta4);
            risp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    risposta(4, false);
                }
            });
            bottoni.add(risp);

            new GestioneGrafica().execute(c, domande);
            while (domande.size() == 0) ;
            cont++;
            caricamento();
        }
    }

    private void caricamento()
    {
        textDomanda.setText( domande.get(domande.size()-1).getDomanda() );
        for(int n = 0 ; n<4 ; n++) {
            String domand = "";
            switch (n) {
                case 0:
                    domand = domande.get(domande.size() - 1).getRisposta_1();
                    break;
                case 1:
                    domand = domande.get(domande.size() - 1).getRisposta_2();
                    break;
                case 2:
                    domand = domande.get(domande.size() - 1).getRisposta_3();
                    break;
                case 3:
                    domand = domande.get(domande.size() - 1).getRisposta_4();
            }
            bottoni.get(n).setText(domand);
        }
    }

    private void risposta(int num, boolean flag){

        //if(conta != null && flag==false)
        //    conta.stop();

        c.invio("$"+num+"$"+0+"$");
        System.out.println("Inviato");

        if(num == -1){
            c.close();
            System.exit(0);
        }

        if(cont<10) {
            new GestioneGrafica().execute(c, domande);
            cont++;
            while( domande.size() != cont );
            caricamento();

        }else {
            String s = "";

            Bundle extras = new Bundle();

            new RiceviRisultati().execute( c, extras);
            while( extras.size() == 0 );

            Intent i = new Intent( ConnectionActivity.this , ResultActivity.class );
            i.putExtras(extras);
            startActivity( i );
        }

    }

    @Override
    protected void onStop()
    {
        risposta(-1, false);
        super.onStop();
    }
}

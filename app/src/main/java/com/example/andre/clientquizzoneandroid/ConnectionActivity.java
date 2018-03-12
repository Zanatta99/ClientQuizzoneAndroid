package com.example.andre.clientquizzoneandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectionActivity extends AppCompatActivity {

    int cont;
    ArrayList<Button> bottoni;
    ArrayList<Domanda> domande = new ArrayList<>();
    TextView textDomanda;
    Client c;
    Domanda dom = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        c = new Client("192.168.1.66");
        System.out.println(c);
        ProgressBar barra = (ProgressBar) findViewById(R.id.progresso);

        new GestioneConnessione(c, barra).execute();

        while( !c.isConnected() );
        System.out.println(c.isConnected());
        new GestionAllacciamento().execute(c);

        while ( !c.isReady() );
        System.out.println(c.isReady());

        setContentView(R.layout.activity_game);

        System.out.println("pronto");

        cont = 0;
        bottoni = new ArrayList<>();

        textDomanda = findViewById(R.id.textViewDomanda);
        Button risp = (Button) findViewById(R.id.buttonRisposta1);
        risp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(1);
                risposta(1,false);
            }
        });
        bottoni.add(risp);
        risp = findViewById(R.id.buttonRisposta2);
        risp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(2);
                risposta(2,false);
            }
        });
        bottoni.add(risp);
        risp = findViewById(R.id.buttonRisposta3);
        risp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(3);
                risposta(3,false);
            }
        });
        bottoni.add(risp);
        risp = findViewById(R.id.buttonRisposta4);
        risp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(4);
                risposta(4,false);
            }
        });
        bottoni.add(risp);

        new GestioneGrafica().execute(c, domande);
        while( domande.size() == 0)
        {
            //System.out.println("Non esco  " + domande.size() + "  " + cont);
        }
        cont++;
        caricamento();
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
            System.out.println("10");
        }

    }
}

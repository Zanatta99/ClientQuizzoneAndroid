package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tVRisultato;
    private TextView tvPunteggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        String ris = bundle.getString("Risultato");

        tVRisultato = findViewById(R.id.textViewRisultato);
        tvPunteggio = findViewById(R.id.textViewPunteggio);
        Button riavvio = findViewById( R.id.buttonRiavvia );

        carica(ris);

        riavvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Intento");
                Intent i = new Intent(ResultActivity.this, StartActivity.class);
                startActivity(i);
            }
        });
    }

    private void carica(String s)
    {
        int n = s.indexOf("$"), n1 = s.indexOf("$", n+1);
        String risultato = s.substring(n+1, n1);
        tVRisultato.setText(risultato);
        n = n1;
        n1 = s.indexOf("$", n+1 );
        String punteggio = s.substring( n+1 , n1);
        tvPunteggio.setText(punteggio);
    }
}

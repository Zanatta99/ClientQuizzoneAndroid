package com.example.andre.clientquizzoneandroid;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button avvio = findViewById( R.id.avvio );

        avvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, ConnectionActivity.class);
                setContentView(R.layout.activity_connection);
                startActivity(i);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if( bundle != null && !bundle.isEmpty() )
        {
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.layoutC), "Errore nella connessione al server", LENGTH_LONG);
            mySnackbar.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                Intent i = new Intent( StartActivity.this, ChangeIpActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

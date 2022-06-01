package com.example.projetbudget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;

public class StartActivity extends AppCompatActivity {

    GestionBD sgbd = new GestionBD(this);
    Intent activity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TestStart","Debut de Start");
        sgbd.open();
        Log.i("TestStart","1");
        //la ligne de desou est a enlevait du comentère pour remètre a zero l'aplli
        //sgbd.videValeur();
        String testvaleur = sgbd.donnerLaValeur();
        Log.i("TestStart","2");
        sgbd.close();
        Log.i("TestStart","TestValeur = "+testvaleur);
        activity2 = new Intent(this, MainActivity.class);
        Log.i("TestStart",""+activity2+"");

        if (testvaleur=="Echec de la requête") {
            Log.i("TestStart", "if = True");

            // a adapté en fonction de l'activité
            setContentView(R.layout.activity_start);
            //TextView text = (TextView) findViewById(R.id.textView);
            TextView erreur = (TextView) findViewById(R.id.textErreur);
            //EditText EDText = (EditText) findViewById(R.id.EDText);
            Button boutton = (Button) findViewById(R.id.button);
            sgbd.open();
            String valeurPrécédante = sgbd.donnerLaValeur();
            sgbd.close();
            //text.setText(valeurPrécédante);


            boutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == boutton) {
                        EditText EDText = (EditText) findViewById(R.id.EDText);
                        String valeur = EDText.getText().toString();
                        Log.i("TestED", "valeur de EDtext : "+valeur);
                        if (valeur.length()>0 && valeur.matches("^[0-9]*")) {
                            Log.i("TestValeur", "le if" + valeur);
                            sgbd.open();
                            sgbd.videValeur();
                            sgbd.creerValeur(valeur);
                            sgbd.close();
                            erreur.setText("");
                            startActivity(activity2);
                            fin();
                        } else {
                            erreur.setText("Un des caractères n'est pas un chiffre");
                        }
                    }
                }
            });
        } else {
            Log.i("TestStart", "if = false");
            startActivity(activity2);
            fin();
        }
    }
    private void fin(){
        this.finish();
    }
}
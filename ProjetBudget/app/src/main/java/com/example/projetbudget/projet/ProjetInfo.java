package com.example.projetbudget.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;

public class ProjetInfo extends AppCompatActivity {

    TextView nom, actuelle, objectif, ajoutText;
    EditText ajoutProj;
    String data1, data2, data3;
    int actu;
    Button modif, ajout, retour1, retour2, enregister, finProj;
    Intent activity;
    boolean fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_info);

        activity = new Intent(this, MainActivity.class);

        nom = findViewById(R.id.TVNom);
        actuelle = findViewById(R.id.TVActuelle);
        objectif = findViewById(R.id.TVObjectif);
        ajoutProj = findViewById(R.id.ETAjoutProj);
        ajout = findViewById(R.id.BProjetSubmit);
        finProj = findViewById(R.id.BProjSuprFin);
        ajoutText = findViewById(R.id.TVAjout);
        enregister = findViewById(R.id.BProjEnregister);
        retour1 = findViewById(R.id.BProjRetour1);
        retour2 = findViewById(R.id.BProjRetour2);

        getData();
        setData();

        if (data2 == data3) {
            fin = true;
            ajout.setVisibility(View.INVISIBLE);
            ajoutProj.setVisibility(View.INVISIBLE);
            ajoutText.setVisibility(View.INVISIBLE);
            enregister.setVisibility(View.INVISIBLE);
            retour1.setVisibility(View.INVISIBLE);
            finProj.setVisibility(View.VISIBLE);
            retour2.setVisibility(View.VISIBLE);
        } else {
            fin = false;
            finProj.setVisibility(View.INVISIBLE);
            retour2.setVisibility(View.INVISIBLE);
        }

        modif = findViewById(R.id.BProjModifier);
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == modif) {

                }
            }
        });

        actu = Integer.parseInt(data2);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ajout) {
                    actu = actu + Integer.parseInt(actuelle.getText().toString());
                    if (actu <= Integer.parseInt(data3)) {
                        actuelle.setText("Actuellement il est de " + actu + " €");
                        ajoutText.setText("");
                    } else {
                        ajoutText.setText("La somme ajouter est trop grande");
                    }
                }
            }
        });
        Log.i("TestProjetInfo", "actu = " + actu);

        retour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == retour1){
                    startActivity(activity);
                }
            }
        });

        retour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == retour2){
                    startActivity(activity);
                }
            }
        });

        enregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == enregister) {

                }
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        nom.setText("Projet : " + data1);
        actuelle.setText("Actuellement il est de "+ data2 + " €");
        objectif.setText("L'objectif du projet et de "  + data3 + " €");
    }
}
package com.example.projetbudget.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbudget.R;

public class ProjetInfo extends AppCompatActivity {

    TextView nom, actuelle, objectif;
    EditText ajoutProj;
    String data1, data2, data3, actu;
    Button modif, ajout, retour, enregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_info);

        nom = findViewById(R.id.TVNom);
        actuelle = findViewById(R.id.TVActuelle);
        objectif = findViewById(R.id.TVObjectif);
        ajoutProj = findViewById(R.id.ETAjoutProj);

        modif = findViewById(R.id.BProjModifier);
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == modif) {

                }
            }
        });

        ajout = findViewById(R.id.BProjetSubmit);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ajout) {

                }
            }
        });

        retour = findViewById(R.id.BProjRetour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == retour){

                }
            }
        });

        enregister = findViewById(R.id.BProjEnregister);
        enregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == enregister) {

                }
            }
        });
        getData();
        setData();

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
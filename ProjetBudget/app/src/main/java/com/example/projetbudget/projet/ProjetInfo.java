package com.example.projetbudget.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbudget.R;

public class ProjetInfo extends AppCompatActivity {

    TextView nom, actuelle, objectif;
    String data1, data2, data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_info);

        nom = findViewById(R.id.TVNom);
        actuelle = findViewById(R.id.TVActuelle);
        objectif = findViewById(R.id.TVObjectif);

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

    private void setData(){
        nom.setText("Projet : " + data1);
        actuelle.setText("Actuellement il est de "+ data2 + " €");
        objectif.setText("L'objectif du projet et de "  + data3 + " €");
    }
}
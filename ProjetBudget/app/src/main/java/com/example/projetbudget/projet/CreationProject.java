package com.example.projetbudget.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;

public class CreationProject extends AppCompatActivity {

    GestionBD sgbd = new GestionBD(this);

    Intent intent;
    EditText nom;
    EditText montant;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_project);

        nom = (EditText)findViewById(R.id.ETNomProjet);
        montant = (EditText)findViewById(R.id.ETMontantProjet);
        submit = (Button)findViewById(R.id.BCreationProjet);

        intent = new Intent(this, com.example.projetbudget.activity.MainActivity.class);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ( v == submit){

                    String PNom = nom.getText().toString();
                    int PMontant = Integer.parseInt(montant.getText().toString());

                    Log.i("TestCreationProject", "nom = " + PNom + ", montant = " + PMontant);
                    sgbd.open();
                    sgbd.nouvProjet(PNom, PMontant, null);
                    sgbd.close();

                    startActivity(intent);
                }
            }
        });
    }
}
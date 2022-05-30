package com.example.projetbudget.projet;

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

public class CreationProjet extends AppCompatActivity {

    GestionBD sgbd = new GestionBD(this);

    Intent intent;
    EditText nom, montant, estim;
    TextView eNom, eMontant, eEstim;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_projet);

        nom = (EditText)findViewById(R.id.ETNomProjet);
        montant = (EditText)findViewById(R.id.ETMontantProjet);
        estim = (EditText)findViewById(R.id.ETEstimationProjet);
        eNom = (TextView)findViewById(R.id.TVErreurMontantProjet);
        eMontant = (TextView) findViewById(R.id.TVErreurMontantProjet);
        eEstim = (TextView)findViewById(R.id.TVErreurEstimationProjet);
        submit = (Button)findViewById(R.id.BCreationProjet);

        intent = new Intent(this, com.example.projetbudget.ui.main.PlaceholderFragment1.class);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ( v == submit){

                    String PNom = nom.getText().toString();
                    int PMontant = Integer.parseInt(montant.getText().toString());
                    int PEstim = Integer.parseInt(estim.getText().toString());

                    if (PNom.length() != 0)
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
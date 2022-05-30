package com.example.projetbudget.projet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;

public class CreationProjet extends AppCompatActivity {

    GestionBD sgbd = new GestionBD(this);

    Intent intent;
    EditText nom, montant, estim;
    TextView eNom, eMontant, eEstim;
    Button submit, retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_projet);

        nom = (EditText)findViewById(R.id.ETNomProjet);
        montant = (EditText)findViewById(R.id.ETMontantProjet);
        estim = (EditText)findViewById(R.id.ETEstimationProjet);
        eNom = (TextView)findViewById(R.id.TVErreurNomProjet);
        eMontant = (TextView) findViewById(R.id.TVErreurMontantProjet);
        eEstim = (TextView)findViewById(R.id.TVErreurEstimationProjet);
        submit = (Button)findViewById(R.id.BCreationProjet);
        retour = (Button)findViewById(R.id.BRetourProjet);

        intent = new Intent(this, MainActivity.class);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ( v == submit){

                    String PNom = nom.getText().toString();
                    String PMontant = montant.getText().toString();
                    String PEstim = estim.getText().toString();

                    int intMontant = Integer.parseInt(PMontant);;

                    boolean bool1;
                    if (PNom.length()>0) {
                        eNom.setText("");
                        bool1 = true;
                    } else {
                        eNom.setText("L'estimation doit comporter au moins un caractère");
                        bool1 = false;
                    }

                    boolean bool2;
                    if (PMontant.length()>0) {
                        if(intMontant>0) {
                            eMontant.setText("");
                            bool2 = true;
                        } else {
                            eMontant.setText("Le montant doit être supérieur a 0");
                            bool2 = false;
                        }
                    } else {
                        eMontant.setText("Le montant doit comporter au moins un caractère");
                        bool2 = false;
                    }

                    boolean bool3;
                    if (PEstim.length()>0) {
                        eEstim.setText("");
                        bool3 = true;
                    } else {
                        eEstim.setText("L'estimation doit comporter au moins un caractère");
                        bool3 = false;
                    }
                    Log.i("TestCreationProject", "test1 = " + bool1 + ", test2 = " + bool2 + ", test3 = " + bool3);
                    if (bool1 == true && bool3 == true && bool2 == true) {
                        Log.i("TestCreationProject", "nom = " + PNom + ", montant = " + PMontant);
                        sgbd.open();
                        sgbd.nouvProjet(PNom, intMontant, null);
                        sgbd.close();
                        int calc = intMontant/Integer.parseInt(PEstim);
                        Toast.makeText(CreationProjet.this, "Le projet sera fini dans " + calc + " jours", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        fin();
                    }

                }
            }
        });
       retour.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(intent);
               fin();
           }
       });
    }
    private void fin(){
        this.finish();
    }
}
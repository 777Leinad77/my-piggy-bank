package com.example.projetbudget.projet;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ProjetInfo extends AppCompatActivity {

    private ProjetInfo theThis;
    TextView nom, actuelle, objectif, ajoutText, textAjout;
    EditText ajoutProj;
    String data1, data2, data3, nomModif;
    String[] nomRecup;
    int actu, testActu, memoir, couleur, niveau;
    Button modif, ajout, retour1, retour2, enregister, finProj;
    Intent activityRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_info);

        this.theThis = this;

        GestionBD sgbd = new GestionBD(this);

        activityRetour = new Intent(this, MainActivity.class);

        nom = findViewById(R.id.TVNom);
        actuelle = findViewById(R.id.TVActuelle);
        objectif = findViewById(R.id.TVObjectif);
        textAjout = findViewById(R.id.TVAjout);
        ajoutProj = findViewById(R.id.ETAjoutProj);
        modif = findViewById(R.id.BProjModifier);
        ajout = findViewById(R.id.BProjetSubmit);
        finProj = findViewById(R.id.BProjSuprFin);
        ajoutText = findViewById(R.id.TVAjoutErreur);
        enregister = findViewById(R.id.BProjEnregister);
        retour1 = findViewById(R.id.BProjRetour1);
        retour2 = findViewById(R.id.BProjRetour2);

        getData();
        setData();

        if (data2.equals(data3)) {
            ajout.setVisibility(View.INVISIBLE);
            ajoutProj.setVisibility(View.INVISIBLE);
            ajoutText.setVisibility(View.INVISIBLE);
            textAjout.setVisibility(View.INVISIBLE);
            enregister.setVisibility(View.INVISIBLE);
            retour1.setVisibility(View.INVISIBLE);
            modif.setVisibility(View.INVISIBLE);
            finProj.setVisibility(View.VISIBLE);
            retour2.setVisibility(View.VISIBLE);
        } else {
            finProj.setVisibility(View.INVISIBLE);
            retour2.setVisibility(View.INVISIBLE);
        }

        nomModif = data1;
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == modif) {
                    nomRecup = nom.getText().toString().split(" : ");
                    ProjetModif projetModif = new ProjetModif(theThis);
                    projetModif.setModifNom(nomRecup[1]);
                    projetModif.retour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nomModif = projetModif.getModifNomView();
                            nom.setText("Projet : " + nomModif);
                            projetModif.dismiss();
                        }
                    });
                    projetModif.suppr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder myPopup1 = new AlertDialog.Builder(theThis);
                            myPopup1.setTitle("Supression");
                            myPopup1.setMessage("Etes vous sur de vouloir supprimer le projet : " + data1 + ", alors qu'il n'est pas fini ?");
                            myPopup1.setPositiveButton("OUi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sgbd.open();
                                    sgbd.supProjet(data1);
                                    sgbd.close();
                                    startActivity(activityRetour);
                                    fin();
                                }
                            });
                            myPopup1.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            myPopup1.show();

                        }
                    });
                    projetModif.build();
                }
            }
        });

        actu = Integer.parseInt(data2);
        memoir = 0;
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ajout) {
                    if (ajoutProj.getText().toString().length()<=0){
                        ajoutText.setText("Il n'y a pas de somme à ajouter");
                    } else if (ajoutProj.getText().toString().matches("^[0-9]*")) {
                        memoir = memoir + Integer.parseInt(ajoutProj.getText().toString());
                        sgbd.open();
                        if (Integer.parseInt(sgbd.donnerLaValeur())>memoir) {
                            testActu = actu + Integer.parseInt(ajoutProj.getText().toString());
                            if (testActu <= Integer.parseInt(data3)) {
                                actu = actu + Integer.parseInt(ajoutProj.getText().toString());
                                actuelle.setText("Actuellement il est de " + actu + " €");
                                ajoutText.setText("");
                                AlertDialog.Builder myPopup2 = new AlertDialog.Builder(theThis);
                                myPopup2.setTitle("Confirmation");
                                myPopup2.setMessage("La somme de " + Integer.parseInt(ajoutProj.getText().toString()) + " a été ajouter au projet " + data1);
                                myPopup2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                myPopup2.show();
                            } else {
                                ajoutText.setText("La somme ajouté est trop grande");

                            }
                        } else {
                            ajoutText.setText("Vous n'avez plus assez de fond dans votre porte-monnaie");
                        }
                    } else {
                        ajoutText.setText("Chiffre uniquement");
                    }
                }
                Log.i("TestProjetInfo", "actu1 = " + actu);
            }
        });

        retour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == retour1){
                    startActivity(activityRetour);
                    fin();
                }
            }
        });

        retour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == retour2){
                    startActivity(activityRetour);
                    fin();
                }
            }
        });

        Log.i("TestProjetInfo", "data1 = " + data1 + ", nomModif = " + nomModif + ", actu = " + actu);
        enregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == enregister) {
                    Log.i("TestProjetInfo", "data1 = " + data1 + ", nomModif = " + nomModif + ", actu = " + actu);
                    sgbd.open();
                    if (actu == Integer.parseInt(data3)) {
                        ajout.setVisibility(View.INVISIBLE);
                        ajoutProj.setVisibility(View.INVISIBLE);
                        ajoutText.setVisibility(View.INVISIBLE);
                        textAjout.setVisibility(View.INVISIBLE);
                        enregister.setVisibility(View.INVISIBLE);
                        retour1.setVisibility(View.INVISIBLE);
                        modif.setVisibility(View.INVISIBLE);
                        finProj.setVisibility(View.VISIBLE);
                        retour2.setVisibility(View.VISIBLE);
                    }
                    Log.i("TestProjetInfo", ""+actu);
                    niveau = 1;
                    if (actu != 0) {
                        couleur = 100 * actu / Integer.parseInt(data3);
                        Log.i("TestProjetInfo", "1 if " + couleur);
                        if (couleur<=50){
                            Log.i("TestProjetInfo", "2 if " + couleur);
                            niveau = 2;
                        } else if (couleur<100){
                            Log.i("TestProjetInfo", "3 if " + couleur);
                            niveau = 3;
                        } else {
                            Log.i("TestProjetInfo", "else " + couleur);
                            niveau = 4;
                        }
                    }
                    sgbd.enregProjet(data1, nomModif, actu, niveau);
                    sgbd.valeurMoins(Integer.toString(memoir));
                    sgbd.close();
                    memoir = 0;
                    Toast.makeText(theThis, "les informations ont bien été enregistrées", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finProj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sgbd.open();
                sgbd.supProjetFini(data1);
                sgbd.close();
                startActivity(activityRetour);
                fin();
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
    private void fin(){
        this.finish();
    }
}
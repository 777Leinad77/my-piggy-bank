package com.example.projetbudget.operation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;
import com.example.projetbudget.metier.TypeOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GainDepense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GestionBD sgbd = new GestionBD(this);
    ArrayList<TypeOperation> categ = new ArrayList<TypeOperation>();
    Spinner spinnerCateg;
    Intent intent;
    Button ad1;
    Button ad2;
    RadioGroup RG;
    RadioButton RB;
    EditText nom;
    EditText montant;
    TextView ENom;
    TextView EMontant;
    TextView ETypeCateg;
    int idcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gain_depense);
        spinnerCateg = (Spinner)findViewById(R.id.STypeOpeP);
        ad1 = (Button)findViewById(R.id.validerP);
        ad2 = (Button)findViewById(R.id.retourP);
        RG = (RadioGroup) findViewById(R.id.RGOpeP);
        RB = (RadioButton)findViewById(R.id.RBDepense);
        RB = (RadioButton)findViewById(R.id.RBGain);
        nom = (EditText)findViewById(R.id.ETNomOpeP);
        montant = (EditText)findViewById(R.id.ETMontantOpeP);
        ENom = (TextView)findViewById(R.id.erreurNomP);
        EMontant = (TextView)findViewById(R.id.erreurMontantP);
        ETypeCateg = (TextView)findViewById(R.id.erreurTypeCategP);


        sgbd.open();
        categ = sgbd.getCateg();
        sgbd.close();

        spinnerCateg.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        for(TypeOperation s : categ){
            if(s.getLibelle()=="categOpe") {
                categories.add(s.getType());
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCateg.setAdapter(dataAdapter2);
        //--------------------



        intent = new Intent(this, MainActivity.class);


        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = RG.getCheckedRadioButtonId();
                RB = findViewById(radioId);

                idcat = spinnerCateg.getSelectedItemPosition();

                String nomValeur = nom.getText().toString();
                String montantValeur = montant.getText().toString();

                Log.i("TestSuppressionDepense", "nous avons : " + RB.getText() + " / " + nomValeur + " / " + idcat + " / " + montantValeur + " .");

                boolean bool1;
                if (nomValeur.length()>0 ) {
                    ENom.setText("");
                    bool1 = true;
                }else {
                    ENom.setText("Le nom doit comporter au moins un caractère");
                    bool1 = false;
                }
                Log.i("TestSuppressionDepense", "bool1 est : " + bool1);

                boolean bool2 = true;
                int montantSint;
                if (montantValeur.length()>0) {
                    montantSint = Integer.parseInt(montantValeur);
                    if (montantSint != 0) {
                        EMontant.setText("");
                        bool2 = true;
                    }else {
                        EMontant.setText("Le montant doit être supérieur a 0");
                        bool2 =false;
                    }
                }else {
                    EMontant.setText("Le montant doit comporter au moins un caractère");
                    bool2 =false;
                }
                Log.i("TestSuppressionDepense", "bool2 est : " + bool2);

                boolean bool3;
                if (idcat != 0) {
                    ETypeCateg.setText("");
                    bool3 = true;
                }else {
                    ETypeCateg.setText("Vous devez choisir un type");
                    bool3 = false;
                }
                Log.i("TestSuppressionDepense", "bool3 est : " + bool3);

                if (bool1 == true && bool2 == true && bool3 == true) {
                    montantSint = Integer.parseInt(montantValeur);
                    sgbd.open();
                    sgbd.nouvOperationP(nomValeur, montantSint, String.valueOf(RB.getText()), idcat);
                    if (RB.getText().length() == 4){
                        sgbd.valeurPlus(montantValeur);
                    }else if(RB.getText().length() == 7) {
                        sgbd.valeurMoins(montantValeur);
                    }
                    sgbd.close();
                    startActivity(intent);
                    fin();
                }
            }

        });
        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                if (c == ad2) {
                    startActivity(intent);
                    fin();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void fin(){
        this.finish();
    }

}
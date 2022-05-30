package com.example.projetbudget.operation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;
import com.example.projetbudget.databinding.GainDepenseBinding;
import com.example.projetbudget.metier.TypeOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GainDepense extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private GainDepenseBinding binding;
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

    public static GainDepense newInstance(int index) {
        GainDepense fragment = new GainDepense();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        GestionBD sgbd = new GestionBD(getContext());

        intent = new Intent(getContext(), MainActivity.class);
        View rootView = inflater.inflate(R.layout.gain_depense, container ,false);


        spinnerCateg = rootView.findViewById(R.id.STypeOpeP);

        RG = rootView.findViewById((R.id.RGOpeP));

        nom = rootView.findViewById(R.id.ETNomOpeP);
        montant = rootView.findViewById(R.id.ETMontantOpeP);

        ENom = rootView.findViewById(R.id.erreurNomP);
        EMontant = rootView.findViewById(R.id.erreurMontantP);
        ETypeCateg = rootView.findViewById(R.id.erreurTypeCategP);

        sgbd.open();
        categ = sgbd.getCateg();
        sgbd.close();

        //spinercategori---------
        spinnerCateg.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        for(TypeOperation s : categ){
            if(s.getLibelle()=="categOpe") {
                categories.add(s.getType());
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCateg.setAdapter(dataAdapter2);
        //--------------------

        ad1 = rootView.findViewById(R.id.validerP);
        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                if (c == ad1) {

                    int radioId = RG.getCheckedRadioButtonId();
                    RB = rootView.findViewById(radioId);

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
                    }
                }
            }
        });
        ad2 = rootView.findViewById(R.id.retourP);
        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                if (c == ad2) {
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
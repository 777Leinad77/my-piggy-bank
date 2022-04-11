package com.example.projetbudget.depanse;

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
import com.example.projetbudget.databinding.SupressionDepenseBinding;
import com.example.projetbudget.metier.TypeFrequence;
import com.example.projetbudget.metier.TypeOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SuppressionDepense extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SupressionDepenseBinding binding;
    ArrayList<TypeFrequence> frequ = new ArrayList<TypeFrequence>();
    Spinner spinnerFrequ;
    ArrayList<TypeOperation> categ = new ArrayList<TypeOperation>();
    Spinner spinnerCateg;
    Intent intent;
    Button ad1;
    Button ad2;
    RadioGroup RG;
    RadioButton RB;
    EditText nom;
    EditText montant;
    EditText dateFrequ;
    TextView ENom;
    TextView EMontant;
    TextView ETypeCateg;
    TextView ETypeFrequ;
    TextView EDateFrequ;
    int idcat;
    int idfrequ;

    public static SuppressionDepense newInstance(int index) {
        SuppressionDepense fragment = new SuppressionDepense();
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
        View rootView = inflater.inflate(R.layout.supression_depense, container ,false);

        spinnerFrequ = rootView.findViewById(R.id.SFrequenceP);
        spinnerCateg = rootView.findViewById(R.id.STypeOpeP);

        RG = rootView.findViewById((R.id.RGOpeP));

        nom = rootView.findViewById(R.id.ETNomOpeP);
        montant = rootView.findViewById(R.id.ETMontantOpeP);
        dateFrequ = rootView.findViewById(R.id.ETDateFrequOpeP);

        ENom = rootView.findViewById(R.id.erreurNomP);
        EMontant = rootView.findViewById(R.id.erreurMontantP);
        ETypeCateg = rootView.findViewById(R.id.erreurTypeCategP);
        ETypeFrequ = rootView.findViewById(R.id.erreuTypeFrequP);
        EDateFrequ = rootView.findViewById(R.id.erreuDateFrequP);

        sgbd.open();
        categ = sgbd.getCateg();
        frequ = sgbd.getFraqu();
        sgbd.close();

        //spinerfrequence---------
        spinnerFrequ.setOnItemSelectedListener(this);
        List<String> fequences = new ArrayList<String>();
        for(TypeFrequence s : frequ){
            if(s.getLibelle()=="fequenceOpe") {
                fequences.add(s.getType());
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, fequences);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerFrequ.setAdapter(dataAdapter1);
        //-------------------------

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
                    idfrequ = spinnerFrequ.getSelectedItemPosition();

                    String nomValeur = nom.getText().toString();
                    String montantValeur = montant.getText().toString();
                    String DateFrequ = dateFrequ.getText().toString();

                    Log.i("TestSuppressionDepense", "nous avons : " + idfrequ + " / " + RB.getText() + " / " + nomValeur + " / " + idcat + " / " + montantValeur + " .");

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

                    boolean bool4;
                    if (DateFrequ.length()>0) {
                        EDateFrequ.setText("");
                        bool4 = true;
                    }else {
                        EDateFrequ.setText("La date doit comporter au moins un caractère");
                        bool4 = false;
                    }
                    Log.i("TestSuppressionDepense", "bool4 est : " + bool4);

                    boolean bool5;
                    if (idfrequ != 0) {
                        ETypeFrequ.setText("");
                        bool5 = true;
                    }else {
                        ETypeFrequ.setText("Vous devez choisir un type");
                        bool5 = false;
                    }
                    Log.i("TestSuppressionDepense", "bool5 est : " + bool5);

                    if (bool1 == true && bool2 == true && bool3 == true && bool4 == true && bool5 == true) {
                        montantSint = Integer.parseInt(montantValeur);
                        sgbd.open();
                        sgbd.nouvOperationP(nomValeur, montantSint, String.valueOf(RB.getText()), idcat, idfrequ, DateFrequ);
                        if (RB.getText() == "Gain"){
                            sgbd.valeurPlus(montantValeur);
                        }else if(RB.getText() == "Dépense") {
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
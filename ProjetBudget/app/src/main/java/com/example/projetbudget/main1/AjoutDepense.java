package com.example.projetbudget.main1;

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
import com.example.projetbudget.databinding.AjoutDepenseBinding;
import com.example.projetbudget.metier.TypeOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class AjoutDepense extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private AjoutDepenseBinding binding;
    ArrayList<TypeOperation> categ = new ArrayList<TypeOperation>();
    Intent intent;
    Button ad;
    RadioGroup RG;
    RadioButton RB;
    EditText nom;
    EditText montant;
    Spinner spinner;
    TextView ENom;
    TextView EMontant;
    int idcat;


    public static AjoutDepense newInstance(int index) {
        AjoutDepense fragment = new AjoutDepense();
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

        intent = new Intent(getContext(), com.example.projetbudget.activity.MainActivity.class);
        View rootView = inflater.inflate(R.layout.ajout_depense, container, false);

        spinner = rootView.findViewById(R.id.STypeOpeS);

        RG = rootView.findViewById((R.id.RGOpeS));

        nom = rootView.findViewById(R.id.ETNomOpeS);
        montant = rootView.findViewById(R.id.ETMontantOpeS);

        ENom = rootView.findViewById(R.id.erreurNom);
        EMontant = rootView.findViewById(R.id.erreurMontant);

        sgbd.open();
        categ = sgbd.getCateg();
        sgbd.close();

        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        for(TypeOperation s : categ){
            if(s.getLibelle()=="categOPe") {
                categories.add(s.getType());
            }
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        ad = rootView.findViewById(R.id.ad);
        ad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View c) {
            if (c == ad) {

                int radioId = RG.getCheckedRadioButtonId();
                RB = rootView.findViewById(radioId);

                String nomValeur = nom.getText().toString();
                String montantValeur = montant.getText().toString();

                Log.i("TestAjoutDepense", "nous avons : " + RB.getText() + " / " + nomValeur + " / " + idcat + " / " + montantValeur + " .");
                if (nomValeur.length()>0 && montantValeur.length()>0 ) {
                    int montantSint = Integer.parseInt(montant.getText().toString());
                    if (montantSint != 0) {
                        sgbd.open();
                        sgbd.nouvOperation(nomValeur, montantSint, String.valueOf(RB.getText()), idcat);
                        if (RB.getText() == "Gain"){
                            sgbd.valeurPlus(montantValeur);
                        }else if(RB.getText() == "Dépense") {
                            sgbd.valeurMoins(montantValeur);
                        }
                        sgbd.close();
                        startActivity(intent);
                    }else {
                        EMontant.setText("Le montant doit être supérieur a 0");
                    }
                }else {
                    ENom.setText("Le nom doit comporter au moins un caractère");
                    EMontant.setText("Le montant doit comporter au moins un caractère");
                }
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
        String catchoisi = parent.getItemAtPosition(position).toString();
        idcat = (int) id;
        Log.i("TestAjoutDepense", catchoisi + " / " +idcat);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

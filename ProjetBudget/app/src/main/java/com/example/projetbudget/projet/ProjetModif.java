package com.example.projetbudget.projet;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.projetbudget.R;

public class ProjetModif extends Dialog {

    String modifNom;
    EditText modifNomView;
    Button suppr,  retour;

    public ProjetModif(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_projet_modif);
        this.modifNom = "Non Projet";
        this.modifNomView = findViewById(R.id.ETModifiNom);
        this.suppr = findViewById(R.id.BProjSupp);
        this.retour = findViewById(R.id.BModifiRetour);
    }

    public Button getRetour() {
        return retour;
    }

    public Button getSuppr() {
        return suppr;
    }

    public void setModifNom(String modifNom) {
        this.modifNom = modifNom;
    }

    public String getModifNomView() {
        return modifNomView.getText().toString();
    }

    public void build() {
        show();
        modifNomView.setText(modifNom);

    }
}
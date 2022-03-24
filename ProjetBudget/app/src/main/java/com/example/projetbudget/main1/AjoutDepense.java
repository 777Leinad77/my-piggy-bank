package com.example.projetbudget.main1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.R;
import com.example.projetbudget.databinding.AjoutDepenseBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class AjoutDepense extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private AjoutDepenseBinding binding;
    Intent intent;
    Button ad;
    RadioGroup RG;
    RadioButton RB;
    TextView nom;
    TextView montant;


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

        intent = new Intent(getContext(), com.example.projetbudget.activity.MainActivity.class);
        View rootView = inflater.inflate(R.layout.ajout_depense, container, false);

        RG = rootView.findViewById((R.id.RGOpeS));

        nom = rootView.findViewById(R.id.ETNomOpeS);
        montant = rootView.findViewById(R.id.ETMontantOpeS);

        ad = rootView.findViewById(R.id.ad);
        ad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View c) {
            if (c == ad) {

                int radioId = RG.getCheckedRadioButtonId();
                RB = rootView.findViewById(radioId);

                String nomS = nom.getText().toString();
                String montantS = montant.getText().toString();

                Log.i("TestAjoutDepense", "nous avons : " + RB.getText() + " / " + nomS + " / " + " / " + montantS + " .");
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
}

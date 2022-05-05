package com.example.projetbudget.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.R;
import com.example.projetbudget.databinding.CreationProjetBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class CreationProjetBiss extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private CreationProjetBinding binding;
    Intent intent;
    TextView nom, actuelle, objectif;
    Button cp;

    public static CreationProjetBiss newInstance(int index) {
        CreationProjetBiss fragment = new CreationProjetBiss();
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
        View rootView = inflater.inflate(R.layout.creation_projet, container, false);

        /*nom = rootView.findViewById(R.id.TVNomProjBiss);
        actuelle = rootView.findViewById(R.id.TVMontActuProjBiss);
        objectif = rootView.findViewById(R.id.TVObjectProjBiss);*/

        cp = rootView.findViewById(R.id.gd);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == cp) {
                    startActivity(intent);
                }
            }

        });

        return rootView;
    }

    /*private void getData() {
        if (getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")){

        }
    }

    private  void setData() {

    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

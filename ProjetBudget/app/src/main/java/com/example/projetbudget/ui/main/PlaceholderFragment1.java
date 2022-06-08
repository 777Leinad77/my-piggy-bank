package com.example.projetbudget.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.Adapter.ProjetAdapter;
import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    RecyclerView RVProjet;
    String[] S1, S2, S3, S4;

    public static PlaceholderFragment1 newInstance(int index) {
        PlaceholderFragment1 fragment = new PlaceholderFragment1();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        RVProjet = rootView.findViewById(R.id.RVProjet);

        GestionBD sgbd = new GestionBD(getContext());

        sgbd.open();

        S1 = sgbd.NomProjet();
        S2 = sgbd.ActuProjet();
        S3 = sgbd.ObjecProjet();
        S4 = sgbd.NiveauProjet();

        sgbd.close();

        ProjetAdapter projetAdapter = new ProjetAdapter(getContext(), S1, S2, S3, S4);
        RVProjet.setAdapter(projetAdapter);
        RVProjet.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
package com.example.projetbudget.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.R;
import com.example.projetbudget.databinding.ModificationProjetBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class ModificationProjet extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ModificationProjetBinding binding;

    public static ModificationProjet newInstance(int index) {
        ModificationProjet fragment = new ModificationProjet();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.modification_projet, container ,false);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
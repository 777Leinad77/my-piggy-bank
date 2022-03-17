package com.example.projetbudget.main1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.R;
import com.example.projetbudget.databinding.SupressionDepenseBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class SuppressionDepense extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SupressionDepenseBinding binding;

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

        View rootView = inflater.inflate(R.layout.supression_depense, container ,false);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
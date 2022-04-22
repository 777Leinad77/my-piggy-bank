package com.example.projetbudget.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.R;
import com.example.projetbudget.activity.GestionDepense;
import com.example.projetbudget.databinding.FragmentMainBinding;
import com.example.projetbudget.projet.CreationProject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    Intent intent;
    Button gp;
    Intent intent1;
    Button gd;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //intent = new Intent(getContext(), com.example.projetbudget.activity.GestionProjet.class);
        intent = new Intent(this.getContext(), CreationProject.class);
        intent1 = new Intent(this.getContext(), GestionDepense.class);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gp = rootView.findViewById(R.id.gp);
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == gp) {
                    startActivity(intent);

                }
            }
        });

        gd = rootView.findViewById(R.id.gd);
        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == gd) {
                    startActivity(intent1);

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

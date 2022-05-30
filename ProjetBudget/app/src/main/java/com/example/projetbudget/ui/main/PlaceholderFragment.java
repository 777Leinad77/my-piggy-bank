package com.example.projetbudget.ui.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.GestionDepense;
import com.example.projetbudget.databinding.FragmentMainBinding;
import com.example.projetbudget.projet.CreationProjet;
import com.example.projetbudget.projet.ProjetInfo;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    Intent intent, intent1 ,intent2 ,intent3;
    Button gp, gd, piff;
    TextView GDP;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GestionBD sgbd = new GestionBD(getContext());


       GDP = rootView.findViewById(R.id.TVGDP);

        sgbd.open();
        GDP.setText(sgbd.donnerLaValeur());
        sgbd.close();
        //Log.i("TestPlaceholderFragment", "S1 = " + S1[0] + " / " + S1[1]);

        intent3 = new Intent(this.getContext(), ProjetInfo.class);
        intent = new Intent(this.getContext(), CreationProjet.class);
        intent1 = new Intent(this.getContext(), GestionDepense.class);

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

    piff = rootView.findViewById(R.id.piff);
        piff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == piff) {
                startActivity(intent2);

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

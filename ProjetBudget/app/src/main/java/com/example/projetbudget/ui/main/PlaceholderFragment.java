package com.example.projetbudget.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;
import com.example.projetbudget.operation.GainDepense;
import com.example.projetbudget.projet.CreationProjet;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private MainActivity LeContext;
    private PageViewModel pageViewModel;

    Intent intent, intent1 ,intent3;
    Button gp, gd;
    TextView GDP;

    public static PlaceholderFragment newInstance( int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment0, container, false);

        GestionBD sgbd = new GestionBD(getContext());


        GDP = rootView.findViewById(R.id.TVGDP);

        sgbd.open();
        GDP.setText(sgbd.donnerLaValeur());
        sgbd.close();
        //Log.i("TestPlaceholderFragment", "S1 = " + S1[0] + " / " + S1[1]);

        //intent = new Intent(getContext(), com.example.projetbudget.activity.GestionProjet.class);
        //intent3 = new Intent(this.getContext(), ProjetInfo.class);
        intent = new Intent(this.getContext(), CreationProjet.class);
        //intent1 = new Intent(this.getContext(), GestionDepense.class);
        intent1 = new Intent(this.getContext(), GainDepense.class);

        gp = rootView.findViewById(R.id.gp);
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == gp) {
                    startActivity(intent);
                    //fin();
                }
            }
        });

        gd = rootView.findViewById(R.id.gd);
        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == gd) {
                    startActivity(intent1);
                    //fin();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    private void fin(){
        LeContext.finish();
    }
}
package com.example.projetbudget.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.Adapter.OperationAdapter;
import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment2 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    RecyclerView RVOperation;
    String[] S1, S2, S3;

    public static PlaceholderFragment2 newInstance(int index) {
        PlaceholderFragment2 fragment = new PlaceholderFragment2();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container ,false);

        RVOperation = rootView.findViewById(R.id.RVOperation);

        GestionBD sgbd = new GestionBD(getContext());

        sgbd.open();

        S1 = sgbd.NomOperation();
        S2 = sgbd.TypeOperation();
        S3 = sgbd.MontantOperation();

        sgbd.close();

        OperationAdapter operationAdapter = new OperationAdapter(getContext(), S1, S2, S3);
        RVOperation.setAdapter((operationAdapter));
        RVOperation.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
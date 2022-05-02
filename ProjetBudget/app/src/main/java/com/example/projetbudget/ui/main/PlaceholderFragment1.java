package com.example.projetbudget.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.R;
import com.example.projetbudget.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    Intent intent3;
    Button btn3;
    RecyclerView RVOperation;
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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
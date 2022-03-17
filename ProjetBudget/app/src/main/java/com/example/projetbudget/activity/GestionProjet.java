package com.example.projetbudget.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projetbudget.databinding.GestionProjetBinding;
import com.example.projetbudget.main.SectionsPagerGestionProjet;
import com.google.android.material.tabs.TabLayout;

public class GestionProjet extends AppCompatActivity {

    private GestionProjetBinding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding2 = GestionProjetBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());

        SectionsPagerGestionProjet SectionsPagerGestionProjet = new SectionsPagerGestionProjet(this, getSupportFragmentManager());
        ViewPager viewPager = binding2.viewPager300;
        viewPager.setAdapter(SectionsPagerGestionProjet);
        TabLayout tabs = binding2.tab300;
        tabs.setupWithViewPager(viewPager);

    }
}

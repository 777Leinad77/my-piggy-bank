package com.example.projetbudget.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projetbudget.databinding.GestionDepenseBinding;
import com.example.projetbudget.main1.SectionsPagerGestionDepense;
import com.google.android.material.tabs.TabLayout;

public class GestionDepense extends AppCompatActivity {

    private GestionDepenseBinding binding3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding3 = GestionDepenseBinding.inflate(getLayoutInflater());
        setContentView(binding3.getRoot());

        SectionsPagerGestionDepense SectionsPagerGestionDepense = new SectionsPagerGestionDepense(this, getSupportFragmentManager());
        ViewPager viewPager = binding3.viewPager400;
        viewPager.setAdapter(SectionsPagerGestionDepense);
        TabLayout tabs = binding3.tab400;
        tabs.setupWithViewPager(viewPager);

    }
}

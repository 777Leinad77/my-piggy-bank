package com.example.projetbudget.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projetbudget.databinding.GestionOperationBinding;
import com.example.projetbudget.operation.SectionsPagerGestionDepense;

public class GestionDepense extends AppCompatActivity {

    private GestionOperationBinding binding3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding3 = GestionOperationBinding.inflate(getLayoutInflater());
        setContentView(binding3.getRoot());

        SectionsPagerGestionDepense SectionsPagerGestionDepense = new SectionsPagerGestionDepense(this, getSupportFragmentManager());
        ViewPager viewPager = binding3.viewPager400;
        viewPager.setAdapter(SectionsPagerGestionDepense);

    }
}

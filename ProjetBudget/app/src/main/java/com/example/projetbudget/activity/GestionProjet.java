package com.example.projetbudget.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projetbudget.databinding.GestionProjetBinding;

public class GestionProjet extends AppCompatActivity {

    private GestionProjetBinding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding2 = GestionProjetBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());


    }
}

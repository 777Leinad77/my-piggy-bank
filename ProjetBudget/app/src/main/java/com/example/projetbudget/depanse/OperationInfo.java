package com.example.projetbudget.depanse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetbudget.R;

public class OperationInfo extends AppCompatActivity {

    TextView nom;
    Button oui, non;
    String data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_info);

        nom = findViewById(R.id.TVNomSuprOpe);
        oui = findViewById(R.id.BOuiSuprOpe);
        non = findViewById(R.id.BNonSuprOpe);

        getData();
        setData();
    }

    private void getData() {
        if (getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")) {
            data1 = getIntent().getStringExtra("data1");
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        nom.setText("voulez vous supprimer " + data1);
    }
}
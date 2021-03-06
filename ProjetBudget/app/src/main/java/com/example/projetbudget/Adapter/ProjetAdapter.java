package com.example.projetbudget.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.R;
import com.example.projetbudget.projet.ProjetInfo;

import java.util.Objects;

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.MyViewHolder> {

    String[] data1, data2, data3, data4;
    Context context;

    public ProjetAdapter(Context ct, String[] nom, String[] acluelle, String[] objectif, String[] niveau) {
        context = ct;
        data1 = nom;
        data2 = acluelle;
        data3 = objectif;
        data4 = niveau;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.projet_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
        if (Objects.equals(data4[position], "1")) {
            holder.couleur.setCardBackgroundColor(0xfff44336);
        } else if (Objects.equals(data4[position], "2")) {
            holder.couleur.setCardBackgroundColor(0xffff6b00);
        } else if (Objects.equals(data4[position], "3")) {
            holder.couleur.setCardBackgroundColor(0xff0094ff);
        } else if (Objects.equals(data4[position], "4")) {
            holder.couleur.setCardBackgroundColor(0xff00ff00);
            holder.myText1.setTextColor(0xf0000000);
            holder.myText2.setTextColor(0xf0000000);
            holder.myText3.setTextColor(0xf0000000);
            holder.slache.setTextColor(0xf0000000);
        } else {
            Toast.makeText(context, "Erreur test Niveau", Toast.LENGTH_SHORT).show();
        }
        Log.i("ProjetAdapter", "le niveau est : " + data4[position]);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjetInfo.class);
                intent.putExtra("data1",data1[position]);
                intent.putExtra("data2",data2[position]);
                intent.putExtra("data3",data3[position]);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3, slache;
        ConstraintLayout constraintLayout;
        CardView couleur;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.TVNomProjet);
            myText2 = itemView.findViewById(R.id.TVMontActu);
            myText3 = itemView.findViewById(R.id.TVObjectProjet);
            slache = itemView.findViewById(R.id.TVSlache);
            couleur = itemView.findViewById(R.id.CVCouleur);
            constraintLayout = itemView.findViewById(R.id.CLProjet);
        }
    }

}

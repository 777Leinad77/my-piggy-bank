package com.example.projetbudget.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.R;

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.MyViewHolder> {

    String[] data1, data2, data3;
    Context context;

    public ProjetAdapter(Context ct, String[] nom, String[] acluelle, String[] objectif) {
        context = ct;
        data1 = nom;
        data2 = acluelle;
        data3 = objectif;
        Log.i("TestProjetAdapter", "data1 = " + data1[0] + " / " + data1[1]);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.project_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.TVNomProjet);
            myText2 = itemView.findViewById(R.id.TVMontActu);
            myText3 = itemView.findViewById(R.id.TVObjectProjet);
        }
    }
}

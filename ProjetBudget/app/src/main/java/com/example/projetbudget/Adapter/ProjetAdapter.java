package com.example.projetbudget.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;
import com.example.projetbudget.projet.ProjetInfo;

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.MyViewHolder> {

    private MainActivity LeContext;
    String[] data1, data2, data3;
    Context context;

    public ProjetAdapter(Context ct, String[] nom, String[] acluelle, String[] objectif) {
        context = ct;
        data1 = nom;
        data2 = acluelle;
        data3 = objectif;

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

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjetInfo.class);
                intent.putExtra("data1",data1[position]);
                intent.putExtra("data2",data2[position]);
                intent.putExtra("data3",data3[position]);
                context.startActivity(intent);
                //fin();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;
        ConstraintLayout constraintLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.TVNomProjet);
            myText2 = itemView.findViewById(R.id.TVMontActu);
            myText3 = itemView.findViewById(R.id.TVObjectProjet);
            constraintLayout = itemView.findViewById(R.id.CLProjet);
        }
    }
    private void fin(){
        LeContext.finish();
    }
}

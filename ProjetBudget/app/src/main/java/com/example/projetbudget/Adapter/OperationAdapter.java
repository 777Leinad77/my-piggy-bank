package com.example.projetbudget.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetbudget.BDD.GestionBD;
import com.example.projetbudget.R;
import com.example.projetbudget.activity.MainActivity;
import com.example.projetbudget.ui.main.PlaceholderFragment1;

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.MyViewHolder> {

    private MainActivity theThis;
    String[] data1, data2, data3;
    Context context;

    public OperationAdapter(Context ct, String[] nom, String[] type, String[] montant){
        context = ct;
        data1 = nom;
        data2 = type;
        data3 = montant;
    }

    @NonNull
    @Override
    public OperationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.operation_row, parent, false);
        return new OperationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationAdapter.MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                GestionBD sgbd = new GestionBD(context);
                AlertDialog.Builder myPopup = new AlertDialog.Builder(context);
                myPopup.setTitle("supretion");
                myPopup.setMessage("êtes-vous sur de vouloir suprimer " + data1[position]);
                myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sgbd.open();
                        sgbd.suprOperation(data1[position]);
                        sgbd.close();
                        context.startActivity(intent);
                        fin();
                    }
                });
                myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                myPopup.show();
                /*Intent intent = new Intent(context, OperationInfo.class);
                intent.putExtra("data1",data1[position]);
                intent.putExtra("data2",data2[position]);
                intent.putExtra("data3",data3[position]);
                context.startActivity(intent);*/
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
            myText1 = itemView.findViewById(R.id.TVNomOperation);
            myText2 = itemView.findViewById(R.id.TVTypeOperation);
            myText3 = itemView.findViewById(R.id.TVMontantOperation);
            constraintLayout = itemView.findViewById(R.id.CLOperation);

        }
    }
    private void fin(){
        theThis.finish();
    }
}

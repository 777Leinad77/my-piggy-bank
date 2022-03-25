package com.example.projetbudget.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetbudget.metier.TypeOperation;

import java.util.ArrayList;
import java.util.Date;

public class GestionBD {

    private SQLiteDatabase maBase;
    private BDHelper bdHelper;

    public GestionBD(Context context) {
        this.bdHelper = new BDHelper(context, "MyPigyBank.db", null,1);
    }

    public void open() {
        maBase = bdHelper.getReadableDatabase();
    }

    public void close() {
        maBase.close();
    }

    public void creerValeur(String valeur) {
        ContentValues cv = new ContentValues();
        cv.put("valeur",valeur);
        maBase.delete("bujetTotal",null, null);
        maBase.insert("bujetTotal",null, cv);
    }

    public String donnerLaValeur() {
        String req = "select valeur from bujetTotal";
        String res = "Echec de la requête";
        Cursor cursor = maBase.rawQuery(req,null,null);
        while (cursor.moveToNext()) {
            res = cursor.getString(0);
        }
        return res;
    }

    public void valeurMoins(String valeur) {
        String res = donnerLaValeur();
        Log.i("TestBD","valeur de res : "+res+", de valeur : "+valeur);
        int intres = Integer.parseInt(res);
        int intvaleur = Integer.parseInt(valeur);
        int calc = intres-intvaleur;
        Log.i("TestBD","valeur apré calcul "+calc);
        creerValeur(String.valueOf(calc));
    }

    public void valeurPlus(String valeur) {
        String res = donnerLaValeur();
        Log.i("TestBD","valeur de res : "+res+", de valeur : "+valeur);
        int intres = Integer.parseInt(res);
        int intvaleur = Integer.parseInt(valeur);
        int calc = intres+intvaleur;
        Log.i("TestBD","valeur apré calcul "+calc);
        creerValeur(String.valueOf(calc));
    }

    public void videValeur() {
        maBase.delete("bujetTotal",null, null);
    }

    public ArrayList<TypeOperation> getCateg(){
        Log.i("TestBD","ouverture de getCateg");
        ArrayList<TypeOperation> Categ = new ArrayList<TypeOperation>();
        Log.i("TestBD","appré ArryList");
        String req1 = "select IdCateg from Categorie";
        Cursor cursor1 = maBase.rawQuery(req1,null,null);
        Log.i("TestBD","cursor1 : ");
        String req2 = "select Type from Categorie";
        Cursor cursor2 = maBase.rawQuery(req2,null,null);
        Log.i("TestBD","cursor2 : ");
        TypeOperation cat0 = new TypeOperation(0, "choisir un type", "categOPe");
        Categ.add(cat0);
        while (cursor1.moveToNext() && cursor2.moveToNext()) {
            TypeOperation cat = new TypeOperation(cursor1.getInt(0), cursor2.getString(0), "categOPe");
            Categ.add(cat);
        }
        return Categ;
    }
    public void nouvOperation(String nom, int montant, String type, int categ ) {
        Date date = new Date();
        ContentValues cv = new ContentValues();
        cv.put("NomOperation",nom);
        cv.put("MontantOp", montant);
        cv.put("Date", String.valueOf(date));
        cv.put("TypeOperation",type);
        cv.put("IdCateg", categ);
        maBase.insert("Operation",null, cv);
    }

}

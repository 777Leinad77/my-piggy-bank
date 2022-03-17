package com.example.projetbudget.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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
        ContentValues cv = new ContentValues();
        cv.put("MontantOp","-"+valeur);
        maBase.insert("Operation",null, cv);
        String res = donnerLaValeur();
        Log.i("TestBD","valeur de res : "+res+", de valeur : "+valeur);
        int intres = Integer.parseInt(res);
        int intvaleur = Integer.parseInt(valeur);
        int calc = intres-intvaleur;
        Log.i("TestBD","valeur apré calcul "+calc);
        creerValeur(""+calc+"");
    }

    public void valeurPlus(String valeur) {
        ContentValues cv = new ContentValues();
        cv.put("MontantOp","+"+valeur);
        maBase.insert("Operation",null, cv);
        String res = donnerLaValeur();
        Log.i("TestBD","valeur de res : "+res+", de valeur : "+valeur);
        int intres = Integer.parseInt(res);
        int intvaleur = Integer.parseInt(valeur);
        int calc = intres+intvaleur;
        Log.i("TestBD","valeur apré calcul "+calc);
        creerValeur(""+calc+"");
    }

    public void videValeur() {
        maBase.delete("bujetTotal",null, null);
    }

    public ArrayList<String> donneLesType() {
        ArrayList<String> lesType = new ArrayList<String>();
        String req = "select Type from Categorie";
        Cursor cursor = maBase.rawQuery(req,null,null);
        int n = 0;
        while (cursor.moveToNext()) {
            lesType.add(cursor.getString(n));
            n += 1;
        }
        Log.i("TestBD","donnerLesType la list "+lesType);
        return lesType;
    }

    public ArrayList<String> donneLesOperation() {
        ArrayList<String> LesOperation = new ArrayList<String>();
        String req = "select MontantOp from Operation";
        Cursor cursor = maBase.rawQuery(req,null,null);
        int n = 0;
        while (cursor.moveToNext()) {
            LesOperation.add(cursor.getString(n));
            n += 1;
        }
        Log.i("TestBD","donnerLesType la list "+LesOperation);
        return LesOperation;
    }
}

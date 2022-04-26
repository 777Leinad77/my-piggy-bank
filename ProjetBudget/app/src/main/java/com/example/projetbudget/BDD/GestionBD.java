package com.example.projetbudget.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetbudget.metier.TypeFrequence;
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
        TypeOperation cat0 = new TypeOperation(0, "choisir un type", "categOpe");
        Categ.add(cat0);
        while (cursor1.moveToNext() && cursor2.moveToNext()) {
            TypeOperation cat = new TypeOperation(cursor1.getInt(0), cursor2.getString(0), "categOpe");
            Categ.add(cat);
        }
        return Categ;

    }

    public ArrayList<TypeFrequence> getFraqu() {
        Log.i("TestBD", "ouverture de getCateg");
        ArrayList<TypeFrequence> Frequ = new ArrayList<TypeFrequence>();
        Log.i("TestBD", "appré ArryList");
        String req1 = "select IdFrequ from Frquence";
        Cursor cursor1 = maBase.rawQuery(req1, null, null);
        Log.i("TestBD", "cursor1 : ");
        String req2 = "select Type from Frquence";
        Cursor cursor2 = maBase.rawQuery(req2, null, null);
        Log.i("TestBD", "cursor2 : ");
        TypeFrequence fre0 = new TypeFrequence(0, "choisir un type", "fequenceOpe");
        Frequ.add(fre0);
        while (cursor1.moveToNext() && cursor2.moveToNext()) {
            TypeFrequence fre = new TypeFrequence(cursor1.getInt(0), cursor2.getString(0), "fequenceOpe");
            Frequ.add(fre);
        }
        return Frequ;
    }

    public void nouvOperationS(String nom, int montant, String type, int categ ) {
        Date date = new Date();
        ContentValues cv = new ContentValues();
        cv.put("NomOperation",nom);
        cv.put("MontantOp", montant);
        cv.put("Date", String.valueOf(date));
        cv.put("TypeOperation",type);
        cv.put("IdCateg", categ);
        maBase.insert("Operation",null, cv);
    }

    public void nouvOperationP(String nom, int montant, String type, int categ, int frequ, String dateFrequ ) {
        Date date = new Date();
        ContentValues cv = new ContentValues();
        cv.put("NomOperation",nom);
        cv.put("MontantOp", montant);
        cv.put("Date", String.valueOf(date));
        cv.put("TypeOperation",type);
        cv.put("IdCateg", categ);
        cv.put("IdFrequ", frequ);
        cv.put("DateFrequ", dateFrequ);
        maBase.insert("Operation",null, cv);
    }

    public void nouvProjet(String nom, int montant, String dateFin) {
        Date date = new Date();
        ContentValues cv = new ContentValues();
        cv.put("Nom", nom);
        cv.put("Objectif", montant);
        cv.put("ObjecActuelle", 0);
        cv.put("DateCréation", String.valueOf(date));
        cv.put("DateObjectif", dateFin);
        maBase.insert("Projet",null, cv);
    }

    //Les info des projet
    //=============================================================================
    public String[] NomProjet() {
        String req = "select Nom from Projet";
        Cursor cursor = maBase.rawQuery(req, null, null);
        String[] Lnom = new String[cursor.getCount()];
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor.getCount());
        while (cursor.moveToNext()) {
            Log.i("TestBD", "cursor = " + cursor.getString(0));
            Lnom[i] = cursor.getString(0);
            i += 1;
        }
        return Lnom;
    }

    public String[] ActuProjet() {
        String req = "select ObjecActuelle from Projet";
        Cursor cursor = maBase.rawQuery(req, null, null);
        String[] Lactu = new String[cursor.getCount()];
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor.getCount());
        while (cursor.moveToNext()) {
            Log.i("TestBD", "cursor = " + cursor.getString(0));
            Lactu[i] = cursor.getString(0);
            i += 1;
        }
        return Lactu;
    }

    public String[] ObjecProjet() {
        String req = "select Objectif from Projet";
        Cursor cursor = maBase.rawQuery(req, null, null);
        String[] Lobjec = new String[cursor.getCount()];
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor.getCount());
        while (cursor.moveToNext()) {
            Log.i("TestBD", "cursor = " + cursor.getString(0));
            Lobjec[i] = cursor.getString(0);
            i += 1;
        }
        return Lobjec;
    }
    //=====================================================================================

    // Les info des operation
    //=====================================================================================
    public String[] NomOperation() {
        String req = "select NomOperation from Operation";
        Cursor cursor = maBase.rawQuery(req, null, null);
        String[] Lnom = new String[cursor.getCount()];
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor.getCount());
        while (cursor.moveToNext()) {
            Log.i("TestBD", "cursor = " + cursor.getString(0));
            Lnom[i] = cursor.getString(0);
            i += 1;
        }
        return Lnom;
    }

    public String[] TypeOperation() {
        String req = "select NomOperation from Operation";
        Cursor cursor = maBase.rawQuery(req, null, null);
        String[] Ltype = new String[cursor.getCount()];
        String[] NomOpe = NomOperation();
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor.getCount());
        while (cursor.moveToNext()) {
            String req1 = "select Type " +
                            "from Categorie " +
                            "INNER JOIN Operation on Operation.IdCateg = Categorie.IdCateg" +
                            "WHERE Operation.NomOperation = " + NomOpe[i] ;
            Cursor cursor1 = maBase.rawQuery(req1, null, null);
            Log.i("TestBD", "cursor = " + cursor1.getString(0));
            Ltype[i] = cursor1.getString(0);
            i += 1;
        }
        return Ltype;
    }

    public String[] MontantOperation() {
        String req1 = "select MontantOp from Operation";
        Cursor cursor1 = maBase.rawQuery(req1, null, null);
        String req2 = "select TypeOperation from Operation";
        Cursor cursor2 = maBase.rawQuery(req2, null, null);
        String[] Lmontant = new String[cursor1.getCount()];
        int i = 0;
        Log.i("TestBD", "cursor = " + cursor1.getCount());
        while (cursor1.moveToNext() && cursor2.moveToNext()) {
            Log.i("TestBD", "cursor = " + cursor1.getString(0));
            if (cursor2.getString(0) == "Dépense") {
                Lmontant[i] = "-" + cursor1.getString(0);
            } else if (cursor2.getString(0) == "Gain"){
                Lmontant[i] = "+" + cursor1.getString(0);
            } else {
                Log.i("TestBD", "ERREUR Dépense ou Gain");
            }
            i += 1;
        }
        return Lmontant;
    }
    //=====================================================================================
}

package com.example.projetbudget.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "create table BujetTotal(Valeur text);";
        db.execSQL(req);
        req = "create table Operation(IdOp INTEGER PRIMARY KEY autoincrement, "
                + "NomOperation text, "
                + "MontantOp INTEGER, "
                + "Date Date, "
                //+ "Heur Heur,"
                + "TypeOperation text,"
                + "DateFrequ Date, "
                + "IdCateg INTEGER, "
                + "IdProj INTEGER, "
                + "IdFrequ INTEGER, "
                + "FOREIGN KEY (IdCateg)"
                + "REFERENCES Categorie (IdCateg),"
                + "FOREIGN KEY (IdProj)"
                + "REFERENCES Projet (IdProj),"
                + "FOREIGN KEY (IdFrequ)"
                + "REFERENCES Frquence (IdFrequ));";
        db.execSQL(req);
        req = "create table Categorie("
                //+ "CategLibelle text, "
                + "Type text, "
                + "IdCateg INTEGER PRIMARY KEY autoincrement "
                + ");";
        db.execSQL(req);
        req = "create table Projet(Nom text, "
                + "Objectif INTEGER, "
                + "ObjecActuelle INTEGER, "
                + "DateCréation Date, "
                + "DateObjectif Date,"
                + "IdProj Integer PRIMARY KEY autoincrement "
                + ");";
        db.execSQL(req);
        req = "create table Frquence("
                + "Type text, "
                + "IdFrequ INTEGER PRIMARY KEY autoincrement "
                + ");";
        db.execSQL(req);
        ContentValues cv1 = new ContentValues();
        cv1.put("Type", "Projet");
        db.insert("Categorie",null, cv1);
        cv1.put("Type", "Vie quotidienne");
        db.insert("Categorie",null, cv1);
        cv1.put("Type", "Loisirs");
        db.insert("Categorie",null, cv1);
        cv1.put("Type", "Autres dépenses");
        db.insert("Categorie",null, cv1);
        cv1.put("Type", "Abonnements et Télephonie");
        db.insert("Categorie",null, cv1);
        cv1.put("Type", "Transports et véhicules");
        db.insert("Categorie",null, cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put("Type", "aujoud'hui");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "journalière");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "hebdomadaire");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "mensuel");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "trimestriel");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "semestriel");
        db.insert("Frquence",null, cv2);
        cv2.put("Type", "annuel");
        db.insert("Frquence",null, cv2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

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
        req = "create table Operation(OpLibelle text, "
                + "MontantOp INTEGER, "
                + "Date Date, "
                + "IdCateg INTEGER, "
                + "IdProj INTEGER, "
                + "FOREIGN KEY (IdCateg)"
                + "REFERENCES Categorie (IdCateg),"
                + "FOREIGN KEY (IdProj)"
                + "REFERENCES Projet (IdProj));";
        db.execSQL(req);
        req = "create table Categorie("
                //+ "CategLibelle text, "
                + "Type text, "
                + "IdCateg INTEGER PRIMARY KEY autoincrement "
                + ");";
        db.execSQL(req);
        req = "create table Projet(Nom text, "
                + "MontantProj INTEGER, "
                + "DeadLine Date, "
                + "IdProj Integer PRIMARY KEY autoincrement "
                + ");";
        db.execSQL(req);
        ContentValues cv = new ContentValues();
        //cv.put("valeur", "Echec");
        //db.insert("BujetTotal",null, cv);
        cv.put("Type", "dépense");
        db.insert("Categorie",null, cv);
        cv.put("Type", "gain");
        db.insert("Categorie",null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

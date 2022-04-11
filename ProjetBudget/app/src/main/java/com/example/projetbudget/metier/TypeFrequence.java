package com.example.projetbudget.metier;

public class TypeFrequence {

        private int id;
        private String type;
        private String libelle;


        public TypeFrequence(int id, String type, String libelle){
            this.id = id;
            this.type = type;
            this.libelle = libelle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }

        @Override
        public String toString() {
            return "categorie{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", libelle='" + libelle + '\'' +
                    '}';
        }
    }


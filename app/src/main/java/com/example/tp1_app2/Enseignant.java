package com.example.tp1_app2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enseignant {

    @Expose
    @SerializedName("pseudo")
    private String prenom;

    @Expose(serialize = false, deserialize = true)
    private String nom;

    public Enseignant(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}

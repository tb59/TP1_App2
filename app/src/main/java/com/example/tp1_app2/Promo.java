package com.example.tp1_app2;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Promo {
    @Expose
    private int annee;
    @Expose
    private String label;
    @Expose
    private ArrayList<Enseignant> enseignants;

    public Promo(int annee, String label) {
        this.annee = annee;
        this.label = label;
        this.enseignants = new ArrayList<Enseignant>();
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void addEnseignant(Enseignant e) {
        this.enseignants.add(e);
    }

    @Override
    public String toString() {
        return "Promo{" +
                "annee=" + annee +
                ", label='" + label + '\'' +
                ", enseignants=" + enseignants.toString() +
                '}';
    }
}

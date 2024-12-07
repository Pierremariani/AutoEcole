package com.example.autoecole.models;

public class Vehicule {

    private String Immatriculation,Marque,Modele;

    private int Annee,CodeCategorie;

    public Vehicule(String immatriculation, String marque, String modele, int annee, int codeCategorie) {
        Immatriculation = immatriculation;
        Marque = marque;
        Modele = modele;
        Annee = annee;
        CodeCategorie = codeCategorie;
    }

    public String getImmatriculation() {
        return Immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        Immatriculation = immatriculation;
    }

    public String getMarque() {
        return Marque;
    }

    @Override
    public String toString() {
        return  Marque+" "+  Modele;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getModele() {
        return Modele;
    }

    public void setModele(String modele) {
        Modele = modele;
    }

    public int getAnnee() {
        return Annee;
    }

    public void setAnnee(int annee) {
        Annee = annee;
    }

    public int getCodeCategorie() {
        return CodeCategorie;
    }

    public void setCodeCategorie(int codeCategorie) {
        CodeCategorie = codeCategorie;
    }
}

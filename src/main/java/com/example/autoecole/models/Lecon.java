package com.example.autoecole.models;

public class Lecon {

    private int codeLecon,codeMoniteur,codeEleve;

    private boolean reglee;

    private String date,immatriculation,heure;

    public Lecon(int codeLecon, String heure, int codeMoniteur, int codeEleve, boolean reglee, String date, String immatriculation) {
        this.codeLecon = codeLecon;
        this.heure = heure;
        this.codeMoniteur = codeMoniteur;
        this.codeEleve = codeEleve;
        this.reglee = reglee;
        this.date = date;
        this.immatriculation = immatriculation;
    }

    public int getCodeLecon() {
        return codeLecon;
    }

    public void setCodeLecon(int codeLecon) {
        this.codeLecon = codeLecon;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getCodeMoniteur() {
        return codeMoniteur;
    }

    public void setCodeMoniteur(int codeMoniteur) {
        this.codeMoniteur = codeMoniteur;
    }

    public int getCodeEleve() {
        return codeEleve;
    }

    public void setCodeEleve(int codeEleve) {
        this.codeEleve = codeEleve;
    }

    public boolean isReglee() {
        return reglee;
    }

    public void setReglee(boolean reglee) {
        this.reglee = reglee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
}

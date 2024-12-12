package com.example.autoecole.models;

public class Licence {

    int codeCategorie,codeMoniteur,codeLicence;

    public Licence(int codeCategorie, int codeMoniteur, int codeLicence) {
        this.codeCategorie = codeCategorie;
        this.codeMoniteur = codeMoniteur;
        this.codeLicence = codeLicence;
    }

    public int getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(int codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public int getCodeMoniteur() {
        return codeMoniteur;
    }

    public void setCodeMoniteur(int codeMoniteur) {
        this.codeMoniteur = codeMoniteur;
    }

    public int getCodeLicence() {
        return codeLicence;
    }

    public void setCodeLicence(int codeLicence) {
        this.codeLicence = codeLicence;
    }

    @Override
    public String toString() {
        return ""+codeCategorie;
    }
}

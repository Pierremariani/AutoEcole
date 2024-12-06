package com.example.autoecole.models;

public class Moniteur {

    private int Code,CodePostal,Tel,numCompte;

    private String nom,prenom,sexe,datedenaissancen,adresse,ville;

    public Moniteur(int code, int codePostal, int tel, int numCompte, String nom, String prenom, String sexe, String datedenaissancen, String adresse, String ville) {
        Code = code;
        CodePostal = codePostal;
        Tel = tel;
        this.numCompte = numCompte;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datedenaissancen = datedenaissancen;
        this.adresse = adresse;
        this.ville = ville;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }

    public int getTel() {
        return Tel;
    }

    public void setTel(int tel) {
        Tel = tel;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom+" "+prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDatedenaissancen() {
        return datedenaissancen;
    }

    public void setDatedenaissancen(String datedenaissancen) {
        this.datedenaissancen = datedenaissancen;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}

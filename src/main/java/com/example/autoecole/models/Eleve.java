package com.example.autoecole.models;

public class Eleve {

    private int code,postal,tel,numCompte;
    private String nom,prenom,ville,adresse,datenaissance,mail,sexe;

    public Eleve(int code, String sexe, int postal, int tel, String nom, String prenom, String ville, String adresse, String datenaissance,String mail,int numCompte) {
        this.code = code;
        this.sexe = sexe;
        this.postal = postal;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.mail = mail;
        this.numCompte = numCompte;
    }


    public Eleve() {

    }

    public Eleve(int CodeEleve, String Nom,String Prenom, String Sexe,String DateDeNaissance, String Adresse1, int CodePostal, String Ville, int Telephone, String mail,int numCompte) {
        this.code = CodeEleve;
        this.nom = Nom;
        this.prenom = Prenom;
        this.sexe = Sexe;
        this.datenaissance = DateDeNaissance;
        this.adresse = Adresse1;
        this.postal = CodePostal;
        this.ville = Ville;
        this.tel = Telephone;
        this.mail = mail;
        this.numCompte = numCompte;
    }

    public Eleve(String Nom,String Prenom, String Sexe,String DateDeNaissance, String Adresse1, int CodePostal, String Ville, int Telephone, String mail,int numCompte) {
        this.nom = Nom;
        this.prenom = Prenom;
        this.sexe = Sexe;
        this.datenaissance = DateDeNaissance;
        this.adresse = Adresse1;
        this.postal = CodePostal;
        this.ville = Ville;
        this.tel = Telephone;
        this.mail = mail;
        this.numCompte = numCompte;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }
}

package com.example.autoecole.models;

public class Categorie {

    private int code;

    private String libelle;

    private Double prix;

    public Categorie(int code, String libelle, Double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "code=" + code +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                '}';
    }

    public Categorie() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}

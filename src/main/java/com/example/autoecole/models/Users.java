package com.example.autoecole.models;

public class Users {
    private int numCompte;
    private String login;

    private String mdpUser;

    private int statut;

    public Users(int numCompte, String login, String mdpUser, int statut) {
        this.numCompte = numCompte;
        this.login = login;
        this.mdpUser = mdpUser;
        this.statut = statut;
    }

    public Users(String login, String mdpUser, int statut) {
        this.login = login;
        this.mdpUser = mdpUser;
        this.statut = statut;
    }

    public Users(String mdpUser, int statut) {
        this.mdpUser = mdpUser;
        this.statut = statut;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Users{" +
                "numCompte=" + numCompte +
                ", login='" + login + '\'' +
                ", mdpUser='" + mdpUser + '\'' +
                ", statut=" + statut +
                '}';
    }
}

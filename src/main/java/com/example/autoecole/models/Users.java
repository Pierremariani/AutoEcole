package com.example.autoecole.models;

public class Users {
    private int idUser;
    private String mdpUser;

    public Users(int idUser, String mdpUser) {
        this.idUser = idUser;
        this.mdpUser = mdpUser;
    }

    public Users(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", mdpUser='" + mdpUser + '\'' +
                '}';
    }
}

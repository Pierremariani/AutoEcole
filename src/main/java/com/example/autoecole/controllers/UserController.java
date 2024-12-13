package com.example.autoecole.controllers;

import com.example.autoecole.models.Users;
import com.example.autoecole.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }
    public ArrayList<Users> getAll() throws SQLException {
        return userService.getAll();
    }

    public void create(String identifiant,String mdpUser, int privilege) throws SQLException {
        userService.create(identifiant,mdpUser,privilege);
    }

    public ArrayList<String> getLogin() throws SQLException {
        return userService.getLogin();
    }

    public boolean verifyLoginMdp(String login, String mdp) throws SQLException {
        return userService.verifyLoginMdp(login,mdp);
    }

    public int getNumCompte(String login) throws SQLException {
        return userService.getNumCompte(login);
    }

    public String getMdp(int numcompte) throws SQLException {
        return userService.getMdp(numcompte);
    }

    public String getlogin(int numcompte) throws SQLException {
        return userService.getlogin(numcompte);
    }

    public void update(int numCompte, String login,String mdp) throws SQLException {
        userService.update(numCompte,login,mdp);
    }

    public int getStatut (String login) throws SQLException {
        return userService.getStatut(login);
    }

    public void updatelogin(int numCompte, String login) throws SQLException {
        userService.updatelogin(numCompte,login);
    }
}

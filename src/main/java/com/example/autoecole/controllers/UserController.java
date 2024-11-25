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
}

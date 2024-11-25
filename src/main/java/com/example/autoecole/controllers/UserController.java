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

    public void create(int idUser,String mdpUser) throws SQLException {
        userService.create(idUser,mdpUser);
    }
}

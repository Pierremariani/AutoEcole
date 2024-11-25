package com.example.autoecole.services;

import com.example.autoecole.models.Users;
import com.example.autoecole.repositories.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    private UserRepository userRepository;
    public UserService() {
        userRepository = new UserRepository();
    }

    public ArrayList<Users> getAll() throws SQLException {
        return userRepository.getAll();
    }

    public void create(String identifiant,String mdpUser, int privilege) throws SQLException {
        Users user = new Users(identifiant,mdpUser,privilege);
        userRepository.create(user);
    }

    public ArrayList<String> getLogin() throws SQLException {
        return userRepository.getLogin();
    }

    public boolean verifyLoginMdp(String login, String mdp) throws SQLException {
        return userRepository.verifyLoginMdp(login,mdp);
    }
}

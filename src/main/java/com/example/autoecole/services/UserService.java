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

    public void create(int idUser, String mdpUser) throws SQLException {
        Users user = new Users(idUser,mdpUser);
        userRepository.create(user);
    }

}

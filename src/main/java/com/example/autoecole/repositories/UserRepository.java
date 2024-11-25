package com.example.autoecole.repositories;

import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements RepositoryInterface<Users, String> {

    private Connection connection;

    public UserRepository()
    {
        connection = DataSourceProvider.getCnx();
    }


    @Override
    public ArrayList<Users> getAll() throws SQLException {
        ArrayList<Users> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT idUser,mdp from user");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Users use = new Users(resultSet.getInt("idUser"),resultSet.getString("mdp"));
            users.add(use);
        }
        return users;
    }

    @Override
    public void create(Users users) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user values(?,?)" );
        preparedStatement.setInt(1,users.getIdUser());
        preparedStatement.setString(2,users.getMdpUser());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public boolean isIdValid(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT idUser from user where idUser = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.wasNull()) {
            return true;
        }
         return false;
    }
}

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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT numCompte,login,motDePasse,statut from compte");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Users use = new Users(resultSet.getInt("numCompte"),resultSet.getString("login"),resultSet.getString("motDePasse"),resultSet.getInt("statut"));
            users.add(use);
        }
        return users;
    }

    @Override
    public void create(Users users) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compte(login,motDePasse,statut) values(?,?,?)" );
        preparedStatement.setString(1,users.getLogin());
        preparedStatement.setString(2,users.getMdpUser());
        preparedStatement.setInt(3,users.getStatut());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public boolean isIdValid(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT numCompte from compte where numCompte = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.wasNull()) {
            return true;
        }
         return false;
    }


    public ArrayList<String> getLogin() throws SQLException {
        ArrayList<String> logins = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login from compte");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            String log = (resultSet.getString("login"));
            logins.add(log);
        }
        return logins;
    }

    public boolean verifyLoginMdp(String login, String mdp) throws SQLException {
        boolean goodlog = false;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT numCompte from compte where login = ? and motDePasse = ?");
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,mdp);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
           goodlog = true;
        }
        return goodlog;
    }

    public int getNumCompte(String login) throws SQLException {
       int num = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT numCompte from compte where login = ?");
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            num = (resultSet.getInt("numCompte"));
        }
        return num;
    }

    public String getlogin(int numcompte) throws SQLException {
        String login = "";
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login from compte where numCompte = ?");
        preparedStatement.setInt(1,numcompte);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            login = (resultSet.getString("login"));
        }
        return login;
    }

    public String getMdp(int numcompte) throws SQLException {
        String mdp = "";
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT motDePasse from compte where numCompte = ?");
        preparedStatement.setInt(1,numcompte);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            mdp = (resultSet.getString("motDePasse"));
        }
        return mdp;
    }

    public void update(int numCompte, String login,String mdp) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE compte SET login=?,motDePasse=? WHERE numCompte = ?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, mdp);
        preparedStatement.setInt(3, numCompte);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}

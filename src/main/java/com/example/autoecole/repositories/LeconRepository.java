package com.example.autoecole.repositories;

import com.example.autoecole.models.Lecon;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeconRepository implements RepositoryInterface<Lecon, String> {

    private Connection connection;

    public LeconRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeEleve = ?");
        preparedStatement.setInt(1,codeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public String nextLeconEleve (int codeEleve) throws SQLException {
        String date="";
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT MIN(Date) FROM lecon WHERE Date > CURRENT_DATE AND CodeEleve = ?");
        preparedStatement.setInt(1,codeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            date = (resultSet.getString("MIN(Date)"));
        }
        return date;
    }

    public int getAllHoursToDo(int CodeEleve) throws SQLException {
        int duree = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(duree) FROM lecon WHERE Date > CURRENT_DATE AND CodeEleve = ?");
        preparedStatement.setInt(1,CodeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            duree = (resultSet.getInt("SUM(duree)"));
        }
        return duree;
    }

    @Override
    public ArrayList<Lecon> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Lecon lecon) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lecon(CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree values(?,?,?,?,?,?,?,?)" );
        preparedStatement.setInt(1,lecon.getCodeLecon());
        preparedStatement.setString(2,lecon.getDate());
        preparedStatement.setString(3,lecon.getHeure());
        preparedStatement.setInt(4,lecon.getCodeMoniteur());
        preparedStatement.setInt(5,lecon.getCodeEleve());
        preparedStatement.setString(6,lecon.getImmatriculation());
        preparedStatement.setBoolean(7,lecon.isReglee());
        preparedStatement.setInt(8,lecon.getDuree());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}

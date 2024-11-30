package com.example.autoecole.repositories;

import com.example.autoecole.models.Lecon;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeconRepository {

    private Connection connection;

    public LeconRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee from lecon where CodeEleve = ?");
        preparedStatement.setInt(1,codeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"));
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
}

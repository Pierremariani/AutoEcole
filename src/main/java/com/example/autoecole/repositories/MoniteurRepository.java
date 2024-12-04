package com.example.autoecole.repositories;

import com.example.autoecole.models.Moniteur;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoniteurRepository implements RepositoryInterface<Moniteur,String> {

    private Connection connection;

    public MoniteurRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    @Override
    public ArrayList<Moniteur> getAll() throws SQLException {
        ArrayList<Moniteur> moniteurs = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeMoniteur,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,numCompte from moniteur");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Moniteur moni = new Moniteur(resultSet.getInt("CodeMoniteur"), resultSet.getInt("CodePostal"), resultSet.getInt("Telephone"), resultSet.getInt("numCompte"), resultSet.getString("Nom"), resultSet.getString("Prenom"), resultSet.getString("Sexe"), resultSet.getString("DateDeNaissance"), resultSet.getString("Adresse1"), resultSet.getString("Ville"));
            moniteurs.add(moni);
        }
        return moniteurs;
    }

    @Override
    public void create(Moniteur moniteur) throws SQLException {

    }
}

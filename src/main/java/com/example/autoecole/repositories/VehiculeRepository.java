package com.example.autoecole.repositories;

import com.example.autoecole.models.Users;
import com.example.autoecole.models.Vehicule;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.*;
import java.util.ArrayList;

public class VehiculeRepository implements RepositoryInterface<Vehicule,String> {

    private Connection connection;

    public VehiculeRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    @Override
    public ArrayList<Vehicule> getAll() throws SQLException {
        return null;
    }

    public ArrayList<Vehicule> getByCodeCategorie(int CodeCategorie ,String Date,String Heure,int duree) throws SQLException {
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Immatriculation,Marque,Modele,Annee,CodeCategorie FROM vehicule v WHERE v.CodeCategorie = ? AND" +
                " v.Immatriculation NOT IN (SELECT l.Immatriculation FROM lecon l WHERE l.Date = ? AND " +
                "l.Immatriculation IS NOT NULL AND ((? BETWEEN l.Heure AND ADDTIME(l.Heure, SEC_TO_TIME(l.duree * 60))) OR " +
                "(ADDTIME(?, SEC_TO_TIME(? * 60)) BETWEEN l.Heure AND ADDTIME(l.Heure, SEC_TO_TIME(l.duree * 60)))))");
        preparedStatement.setInt(1,CodeCategorie);
        preparedStatement.setString(2,Date);
        preparedStatement.setString(3,Heure);
        preparedStatement.setString(4,Heure);
        preparedStatement.setInt(5,duree);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Vehicule v = new Vehicule(resultSet.getString("Immatriculation"),resultSet.getString("Marque"),resultSet.getString("Modele"),resultSet.getInt("Annee"),resultSet.getInt("CodeCategorie"));
            vehicules.add(v);
        }
        return vehicules;
    }

    @Override
    public void create(Vehicule vehicule) throws SQLException {

    }
}

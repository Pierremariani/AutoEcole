package com.example.autoecole.repositories;

import com.example.autoecole.models.Users;
import com.example.autoecole.models.Vehicule;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT v.Immatriculation,v.Marque,v.Modele,Annee,CodeCategorie FROM vehicule v left join lecon l on v.Immatriculation = l.Immatriculation" +
                    " WHERE v.CodeCategorie = ? AND" +
                    " v.Immatriculation NOT IN" +
                    " (SELECT l.Immatriculation" +
                    " FROM lecon l " +
                    "WHERE l.Date = ? AND " +
                    " ? >= l.Heure " +
                    "AND ? < l.Heure+l.duree);");

            preparedStatement.setInt(1,CodeCategorie);
            preparedStatement.setString(2,Date);
            preparedStatement.setString(3,Heure);
            preparedStatement.setString(4,Heure);
            ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(Date);
        System.out.println(Heure);

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

package com.example.autoecole.repositories;

import com.example.autoecole.models.Categorie;
import com.example.autoecole.models.Moniteur;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.*;
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

    public ArrayList<Moniteur> getMoniteur(int codecategorie) throws SQLException {
        ArrayList<Moniteur> moniteurs = new ArrayList<>();
        ArrayList<Integer> codemoniteurs = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeMoniteur from licence where CodeCategorie = ?");
        preparedStatement.setInt(1,codecategorie);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            int a = (resultSet.getInt("CodeMoniteur"));
            codemoniteurs.add(a);
        }

        for (int i = 0; i < codemoniteurs.size();i++) {
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT CodeMoniteur,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,numCompte from moniteur where CodeMoniteur =?");
            preparedStatement2.setInt(1,codemoniteurs.get(i));
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet2.next()) {
                Moniteur moni = new Moniteur(resultSet2.getInt("CodeMoniteur"), resultSet2.getInt("CodePostal"), resultSet2.getInt("Telephone"), resultSet2.getInt("numCompte"), resultSet2.getString("Nom"), resultSet2.getString("Prenom"), resultSet2.getString("Sexe"), resultSet2.getString("DateDeNaissance"), resultSet2.getString("Adresse1"), resultSet2.getString("Ville"));
                moniteurs.add(moni);
            }
        }
        return moniteurs;
    }

    @Override
    public void create(Moniteur moniteur) throws SQLException {

    }
}

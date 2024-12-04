package com.example.autoecole.repositories;

import com.example.autoecole.models.Categorie;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieRepository implements RepositoryInterface<Categorie,String> {

    private Connection connection;

    public CategorieRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    public Categorie getCategorie(int CodeCategorie) throws SQLException {
        Categorie c = new Categorie();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeCategorie,Libelle,Prix from categorie where CodeCategorie = ?");
        preparedStatement.setInt(1,CodeCategorie);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            c = new Categorie(resultSet.getInt("CodeCategorie"),resultSet.getString("Libelle"),resultSet.getDouble("Prix"));
        }
        return c;
    }


    public ArrayList<Integer> getAllCategorieEleve(int CodeEleve) throws SQLException {
        ArrayList<Integer> categories = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeCategorie from eleve_categorie where CodeEleve = ?");
        preparedStatement.setInt(1,CodeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
             int cat = (resultSet.getInt("CodeCategorie"));
             categories.add(cat);
        }
        return categories;
    }


    @Override
    public ArrayList<Categorie> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Categorie categorie) throws SQLException {

    }
}

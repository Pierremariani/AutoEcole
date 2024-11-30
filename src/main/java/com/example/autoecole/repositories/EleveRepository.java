package com.example.autoecole.repositories;

import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EleveRepository {
    private Connection connection;

    public EleveRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    public void createEleve(Eleve eleve) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO eleve(CodeEleve,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,mail,numCompte) values(?,?,?,?,?,?,?,?,?,?,?)" );
        preparedStatement.setInt(1,eleve.getCode());
        preparedStatement.setString(2,eleve.getNom());
        preparedStatement.setString(3,eleve.getPrenom());
        preparedStatement.setString(4,eleve.getSexe());
        preparedStatement.setString(5,eleve.getDatenaissance());
        preparedStatement.setString(6,eleve.getAdresse());
        preparedStatement.setInt(7,eleve.getPostal());
        preparedStatement.setString(8,eleve.getVille());
        preparedStatement.setInt(9,eleve.getTel());
        preparedStatement.setString(10, eleve.getMail());
        preparedStatement.setInt(11, eleve.getNumCompte());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public int GenerateCodeEleve() throws SQLException {
        int code = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(CodeEleve) from eleve");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            code = (resultSet.getInt("max(CodeEleve)"));
        }
        return code+1;
    }

    public Eleve setCurrentEleve(int numCompte) throws SQLException {
        Eleve e = new Eleve();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeEleve,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,mail from eleve where numCompte = ?");
        preparedStatement.setInt(1,numCompte);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            e = new Eleve(resultSet.getInt("CodeEleve"),resultSet.getString("Nom"),resultSet.getString("Prenom"),resultSet.getString("Sexe"),resultSet.getString("DateDeNaissance"),resultSet.getString("Adresse1"),resultSet.getInt("CodePostal"),resultSet.getString("Ville"),resultSet.getInt("Telephone"),resultSet.getString("mail"),numCompte);
        }
        return e;
    }
}

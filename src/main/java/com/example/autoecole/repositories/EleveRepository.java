package com.example.autoecole.repositories;

import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Moniteur;
import com.example.autoecole.models.Users;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EleveRepository implements RepositoryInterface<Eleve,String>{
    private Connection connection;

    public EleveRepository()
    {
        connection = DataSourceProvider.getCnx();
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

    public ArrayList<Moniteur> getAllMoniteurByEleve(int CodeEleve) throws SQLException {
        ArrayList<Moniteur> moniteurs = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeMoniteur,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,numCompte from moniteur where CodeMoniteur IN (SELECT DISTINCT(CodeMoniteur) from lecon where CodeEleve = ?)");
        preparedStatement.setInt(1,CodeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            try {
                Moniteur moni = new Moniteur(resultSet.getInt("CodeMoniteur"), resultSet.getInt("CodePostal"), resultSet.getInt("Telephone"), resultSet.getInt("numCompte"), resultSet.getString("Nom"), resultSet.getString("Prenom"), resultSet.getString("Sexe"), resultSet.getString("DateDeNaissance"), resultSet.getString("Adresse1"), resultSet.getString("Ville"));
                moniteurs.add(moni);
            } catch (NumberFormatException | SQLException e) {
                System.err.println("Erreur lors du traitement d'un moniteur : " + e.getMessage());
                System.err.println("Cette erreur est surement lié au fait qu'une leçon de cet élève ait été mal insérée dans la bdd");
            }
        }
        return moniteurs;
    }

    public int getAllHeures(int CodeEleve) throws SQLException {
        int heure = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select sum(duree) from lecon where CodeEleve = ?");
        preparedStatement.setInt(1, CodeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            heure = resultSet.getInt("sum(duree)");
        }
        return heure;
    }

    @Override
    public ArrayList<Eleve> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Eleve eleve) throws SQLException {
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
}

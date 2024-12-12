package com.example.autoecole.repositories;

import com.example.autoecole.models.Licence;
import com.example.autoecole.tools.DataSourceProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class licenceRepository {

    private Connection connection;

    public licenceRepository()
    {
        connection = DataSourceProvider.getCnx();
    }

    public int GenerateLicenceCode() throws SQLException {
        int code = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(CodeLicence) from licence");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            code = (resultSet.getInt("max(CodeLicence)"));
        }
        return code+1;
    }

    public void addLicence(int CodeMoniteur,int CodeCategorie,int CodeLicence) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO licence(CodeLicence,CodeMoniteur,CodeCategorie) values(?,?,?)");
        preparedStatement.setInt(1,CodeLicence);
        preparedStatement.setInt(2,CodeMoniteur);
        preparedStatement.setInt(3,CodeCategorie);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<Licence> getLicenceManquante(int CodeMoniteur) throws SQLException {
        ArrayList<Licence> licences = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT CodeLicence,CodeMoniteur,CodeCategorie from licence WHERE " +
                "CodeCategorie NOT IN (SELECT DISTINCT CodeCategorie from licence where CodeMoniteur = ?)");
        preparedStatement.setInt(1,CodeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Licence l =new Licence(resultSet.getInt("CodeCategorie"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeLicence"));
            licences.add(l);
        }
        return licences;
    }
}

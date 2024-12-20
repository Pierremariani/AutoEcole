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

    public boolean isDateAvailable(String Date) throws SQLException {
        boolean available = false;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CASE WHEN ? > CURRENT_DATE THEN 1 ELSE 0 END AS isFuture");
        preparedStatement.setString(1,Date);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            available = resultSet.getBoolean("isFuture");
        }
        return available;
    }


    @Override
    public ArrayList<Lecon> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Lecon lecon) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lecon(CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree) values(?,?,?,?,?,?,?,?)" );
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

    public int GenerateCodeLecon() throws SQLException {
        int code = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(CodeLecon) from lecon");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            code = (resultSet.getInt("max(CodeLecon)"));
        }
        return code+1;
    }

    public ArrayList<Lecon> getAllLeconComingByEleve(int codeEleve) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeEleve = ? and Date >= CURRENT_DATE");
        preparedStatement.setInt(1,codeEleve);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteur(int codeMoniteur) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeMoniteur = ? and Date >= CURRENT_DATE");
        preparedStatement.setInt(1,codeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public int nbleconjour(int CodeMoniteur) throws SQLException {
        int nb = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(CodeLecon) FROM lecon WHERE Date = CURRENT_DATE AND CodeMoniteur = ?");
        preparedStatement.setInt(1,CodeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            nb = (resultSet.getInt("count(CodeLecon)"));
        }
        return nb;
    }

    public int nbleconsemaine(int CodeMoniteur) throws SQLException {
        int nb = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(CodeLecon) FROM lecon WHERE Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 1 WEEK) AND CodeMoniteur = ?");
        preparedStatement.setInt(1,CodeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            nb = (resultSet.getInt("count(CodeLecon)"));
        }
        return nb;
    }

    public int nbleconmois(int CodeMoniteur) throws SQLException {
        int nb = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(CodeLecon) FROM lecon WHERE Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH) AND CodeMoniteur = ?");
        preparedStatement.setInt(1,CodeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            nb = (resultSet.getInt("count(CodeLecon)"));
        }
        return nb;
    }

    public int nblecontrimestre(int CodeMoniteur) throws SQLException {
        int nb = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(CodeLecon) FROM lecon WHERE Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 3 MONTH) AND CodeMoniteur = ?");
        preparedStatement.setInt(1,CodeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            nb = (resultSet.getInt("count(CodeLecon)"));
        }
        return nb;
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurjour(int codeMoniteur) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeMoniteur = ? and Date = CURRENT_DATE");
        preparedStatement.setInt(1,codeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteursemaine(int codeMoniteur) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeMoniteur = ? and Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 1 WEEK)");
        preparedStatement.setInt(1,codeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurmois(int codeMoniteur) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeMoniteur = ? and Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH)");
        preparedStatement.setInt(1,codeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurtrimestre(int codeMoniteur) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CodeLecon,Date,Heure,CodeMoniteur,CodeEleve,Immatriculation,Reglee,duree from lecon where CodeMoniteur = ? and Date >= CURRENT_DATE AND Date <= DATE_ADD(CURRENT_DATE, INTERVAL 3 MONTH)");
        preparedStatement.setInt(1,codeMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Lecon lec = new Lecon(resultSet.getInt("CodeLecon"),resultSet.getString("Heure"),resultSet.getInt("CodeMoniteur"),resultSet.getInt("CodeEleve"),resultSet.getBoolean("Reglee"),resultSet.getString("Date"),resultSet.getString("Immatriculation"),resultSet.getInt("duree"));
            lecons.add(lec);
        }
        return lecons;
    }
}

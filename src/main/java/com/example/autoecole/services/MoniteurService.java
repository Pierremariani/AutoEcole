package com.example.autoecole.services;

import com.example.autoecole.models.Moniteur;
import com.example.autoecole.repositories.EleveRepository;
import com.example.autoecole.repositories.MoniteurRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class MoniteurService {

    private MoniteurRepository moniteurRepository;
    public MoniteurService() {
        moniteurRepository = new MoniteurRepository();
    }

    public ArrayList<Moniteur> getAll() throws SQLException {
        return moniteurRepository.getAll();
    }

    public ArrayList<Moniteur> getMoniteur(int codecategorie, String date , String heure) throws SQLException {
        return moniteurRepository.getMoniteur(codecategorie,date,heure);
    }

    public Moniteur setCurrentMoniteur(int numCompte) throws SQLException {
        return moniteurRepository.setCurrentMoniteur(numCompte);
    }

    public void update(String Nom, String Prenom,String Sexe,String DateDeNaissance,String Adresse1,int CodePostal,String Ville,int Telephone,int numCompte) throws SQLException {
        Moniteur m = new Moniteur(Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,numCompte);
        moniteurRepository.update(m);
    }
}

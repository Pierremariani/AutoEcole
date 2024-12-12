package com.example.autoecole.controllers;

import com.example.autoecole.models.Moniteur;
import com.example.autoecole.services.EleveService;
import com.example.autoecole.services.MoniteurService;

import java.sql.SQLException;
import java.util.ArrayList;

public class MoniteurController {

    private MoniteurService moniteurService;

    public MoniteurController() {
        moniteurService = new MoniteurService();
    }

    public ArrayList<Moniteur> getAll() throws SQLException {
        return moniteurService.getAll();
    }

    public ArrayList<Moniteur> getMoniteur(int codecategorie, String date,String heure) throws SQLException {
        return moniteurService.getMoniteur(codecategorie,date,heure);
    }

    public Moniteur setCurrentMoniteur(int numCompte) throws SQLException {
        return moniteurService.setCurrentMoniteur(numCompte);
    }

    public void update(String Nom, String Prenom,String Sexe,String DateDeNaissance,String Adresse1,int CodePostal,String Ville,int Telephone,int numCompte) throws SQLException {
        moniteurService.update(Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone,numCompte);
    }
}

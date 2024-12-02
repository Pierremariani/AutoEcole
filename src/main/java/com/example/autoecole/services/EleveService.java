package com.example.autoecole.services;

import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Moniteur;
import com.example.autoecole.repositories.EleveRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class EleveService {

    private EleveRepository eleveRepository;
    public EleveService() {
        eleveRepository = new EleveRepository();
    }

    public int GenerateCodeEleve() throws SQLException {
        return eleveRepository.GenerateCodeEleve();
    }

    public Eleve setCurrentEleve(int numCompte) throws SQLException {
        return eleveRepository.setCurrentEleve(numCompte);
    }

    public ArrayList<Moniteur> getAllMoniteurByEleve(int CodeEleve) throws SQLException {
        return eleveRepository.getAllMoniteurByEleve(CodeEleve);
    }

    public void createEleve(int CodeEleve, String Nom, String Prenom, String Sexe, String DateDeNaissance, String Adresse1, int CodePostal, String Ville, int Telephone, String mail, int numCompte) throws SQLException {
        Eleve eleve = new Eleve(CodeEleve,Sexe,CodePostal,Telephone,Nom,Prenom,Ville,Adresse1, DateDeNaissance,mail,numCompte);
        eleveRepository.createEleve(eleve);
    }
}

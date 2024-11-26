package com.example.autoecole.services;

import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Users;
import com.example.autoecole.repositories.EleveRepository;
import com.example.autoecole.repositories.UserRepository;

import java.sql.Date;
import java.sql.SQLException;

public class EleveService {

    private EleveRepository eleveRepository;
    public EleveService() {
        eleveRepository = new EleveRepository();
    }

    public int GenerateCodeEleve() throws SQLException {
        return eleveRepository.GenerateCodeEleve();
    }

    public void createEleve(int CodeEleve,String Nom, String Prenom,int Sexe,String DateDeNaissance,String Adresse1,int CodePostal,String Ville,int Telephone) throws SQLException {
        Eleve eleve = new Eleve(CodeEleve,Sexe,CodePostal,Telephone,Nom,Prenom,Ville,Adresse1, DateDeNaissance);
        eleveRepository.createEleve(eleve);
    }
}

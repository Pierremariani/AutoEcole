package com.example.autoecole.controllers;


import com.example.autoecole.models.Users;
import com.example.autoecole.services.EleveService;
import com.example.autoecole.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class EleveController {
    private EleveService eleveService;

    public EleveController() {
        eleveService = new EleveService();
    }

    public int GenerateCodeEleve() throws SQLException {
        return eleveService.GenerateCodeEleve();
    }

    public void createEleve(int CodeEleve,String Nom, String Prenom,int Sexe,String DateDeNaissance,String Adresse1,int CodePostal,String Ville,int Telephone) throws SQLException {
        eleveService.createEleve(CodeEleve,Nom,Prenom,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone);
    }

}

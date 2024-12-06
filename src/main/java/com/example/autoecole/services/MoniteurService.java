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

    public ArrayList<Moniteur> getMoniteur(int codecategorie) throws SQLException {
        return moniteurRepository.getMoniteur(codecategorie);
    }
}

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
}

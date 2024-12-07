package com.example.autoecole.controllers;

import com.example.autoecole.models.Vehicule;
import com.example.autoecole.services.UserService;
import com.example.autoecole.services.VehiculeService;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculeController {

    private VehiculeService vehiculeService;

    public VehiculeController() {
        vehiculeService = new VehiculeService();
    }

    public ArrayList<Vehicule> getByCodeCategorie(int CodeCategorie ,String Date,String Heure,int duree) throws SQLException {
        return vehiculeService.getByCodeCategorie(CodeCategorie,Date,Heure,duree);
    }
}

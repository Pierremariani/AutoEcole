package com.example.autoecole.services;

import com.example.autoecole.models.Vehicule;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.repositories.VehiculeRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculeService {

    private VehiculeRepository vehiculeRepository;
    public VehiculeService() {
        vehiculeRepository = new VehiculeRepository();
    }

    public ArrayList<Vehicule> getByCodeCategorie(int CodeCategorie ,String Date,String Heure,int duree) throws SQLException {
        return vehiculeRepository.getByCodeCategorie(CodeCategorie,Date,Heure,duree);
    }

    public Double getPrixbyImmatriculation(String Immatriculation) throws SQLException {
        return vehiculeRepository.getPrixbyImmatriculation(Immatriculation);
    }

}
